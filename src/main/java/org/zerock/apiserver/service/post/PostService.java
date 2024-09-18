package org.zerock.apiserver.service.post;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.PostingSearchRequestDTO;
import org.zerock.apiserver.dto.post.PostDto;
import org.zerock.apiserver.dto.post.CreatePostDto;
import org.zerock.apiserver.dto.post.PostOperationResult;

import java.util.List;

public interface PostService {
    PageResponseDtoMini<PostDto> search(PostingSearchRequestDTO postingSearchRequestDTO);
    PostOperationResult saveOrUpdatePosts(List<CreatePostDto> postDtoList);
    int removePosts(List<Long> postIds);
}