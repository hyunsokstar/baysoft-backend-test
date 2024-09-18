package org.zerock.apiserver.repository.post;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.PostingSearchRequestDTO;
import org.zerock.apiserver.dto.post.PostDto;

public interface PostSearch {
    PageResponseDtoMini<PostDto> search(PostingSearchRequestDTO postingSearchRequestDTO);
}