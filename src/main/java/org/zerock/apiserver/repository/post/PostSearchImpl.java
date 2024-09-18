package org.zerock.apiserver.repository.post;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.Post;
import org.zerock.apiserver.domain.QPost;
import org.zerock.apiserver.domain.QBoard;
import org.zerock.apiserver.dto.post.PostDto;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.PostingSearchRequestDTO;
import org.zerock.apiserver.dto.mapper.PostMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.zerock.apiserver.domain.QComment.comment;

public class PostSearchImpl extends QuerydslRepositorySupport implements PostSearch {

    public PostSearchImpl() {
        super(Post.class);
    }

    @Override
    public PageResponseDtoMini<PostDto> search(PostingSearchRequestDTO postingSearchRequestDTO) {
        BooleanBuilder builder = getSearchConditions(postingSearchRequestDTO);
        QPost post = QPost.post;
        QBoard board = QBoard.board;

        JPQLQuery<Post> query = from(post)
                .leftJoin(post.board, board).fetchJoin()
                .leftJoin(post.comments, comment).fetchJoin()
                .where(builder)
                .distinct();

        Pageable pageable = getPageable(postingSearchRequestDTO);
        this.getQuerydsl().applyPagination(pageable, query);

        List<PostDto> dtoList = query.fetch().stream()
                .map(PostMapper::entityToDto)
                .collect(Collectors.toList());

        long total = query.fetchCount();

        return PageResponseDtoMini.<PostDto>builder()
                .content(dtoList)
                .totalCount((int) total)
                .currentPage(postingSearchRequestDTO.getPage())
                .build();
    }

    private BooleanBuilder getSearchConditions(PostingSearchRequestDTO postingSearchRequestDTO) {
        BooleanBuilder builder = new BooleanBuilder();
        QPost post = QPost.post;

        String keyword = postingSearchRequestDTO.getSearchKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            switch (postingSearchRequestDTO.getSearchType()) {
                case "title":
                    builder.and(post.title.contains(keyword));
                    break;
                case "content":
                    builder.and(post.content.contains(keyword));
                    break;
                default:
                    builder.and(post.title.contains(keyword).or(post.content.contains(keyword)));
            }
        }

        // 특정 게시판의 게시물만 검색
        if (postingSearchRequestDTO.getBoardId() != null) {
            builder.and(post.board.boardId.eq(postingSearchRequestDTO.getBoardId()));
        }

        return builder;
    }

    private Pageable getPageable(PostingSearchRequestDTO postingSearchRequestDTO) {
        return PageRequest.of(
                postingSearchRequestDTO.getPage() - 1,
                postingSearchRequestDTO.getSize(),
                Sort.by("postId").descending()
        );
    }
}