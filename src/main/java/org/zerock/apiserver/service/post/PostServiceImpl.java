package org.zerock.apiserver.service.post;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Post;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.PostingSearchRequestDTO;
import org.zerock.apiserver.dto.post.PostDto;
import org.zerock.apiserver.dto.post.CreatePostDto;
import org.zerock.apiserver.dto.post.PostOperationResult;
import org.zerock.apiserver.repository.post.PostRepository;
import org.zerock.apiserver.repository.board.BoardRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    @Override
    public PageResponseDtoMini<PostDto> search(PostingSearchRequestDTO postingSearchRequestDTO) {
        return postRepository.search(postingSearchRequestDTO);
    }

    @Override
    @Transactional
    public PostOperationResult saveOrUpdatePosts(List<CreatePostDto> postDtoList) {
        int updatedCount = 0;
        int createdCount = 0;

        for (CreatePostDto dto : postDtoList) {
            if (dto.getPostId() == null) {
                createPost(dto);
                createdCount++;
            } else {
                Post existingPost = postRepository.findById(dto.getPostId())
                        .orElseThrow(() -> new NoSuchElementException("Post with ID " + dto.getPostId() + " not found"));
                updatePost(existingPost, dto);
                updatedCount++;
            }
        }

        return new PostOperationResult(updatedCount, createdCount);
    }

    private void createPost(CreatePostDto dto) {
        Post post = new Post();
        updatePostFromDto(post, dto);
        post.setRegDt(LocalDate.now());
        postRepository.save(post);
    }

    private void updatePost(Post post, CreatePostDto dto) {
        updatePostFromDto(post, dto);
        post.setUptDt(LocalDate.now());
        postRepository.save(post);
    }

    private void updatePostFromDto(Post post, CreatePostDto dto) {
        Optional.ofNullable(dto.getTitle()).ifPresent(post::setTitle);
        Optional.ofNullable(dto.getContent()).ifPresent(post::setContent);
        Optional.ofNullable(dto.getUserId()).ifPresent(post::setUserId);
        Optional.ofNullable(dto.getViewCount()).ifPresent(post::setViewCount);

        Optional.ofNullable(dto.getBoardId()).ifPresent(boardId -> {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new NoSuchElementException("Board with ID " + boardId + " not found"));
            post.setBoard(board);
        });
    }

    @Override
    @Transactional
    public int removePosts(List<Long> postIds) {
        int deletedCount = 0;
        for (Long postId : postIds) {
            if (postRepository.existsById(postId)) {
                postRepository.deleteById(postId);
                deletedCount++;
            } else {
                log.warn("삭제하려는 게시글이 존재하지 않습니다: PostId = " + postId);
            }
        }
        return deletedCount;
    }
}