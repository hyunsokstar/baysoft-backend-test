package org.zerock.apiserver.dto.mapper;

import org.zerock.apiserver.domain.Post;
import org.zerock.apiserver.dto.post.PostDto;

import java.util.Optional;

public class PostMapper {
    public static PostDto entityToDto(Post post) {
        return Optional.ofNullable(post).map(p -> PostDto.builder()
                .postId(p.getPostId())
                .board(p.getBoard() != null ? BoardMapper.entityToDto(p.getBoard()) : null)
                .title(p.getTitle())
                .content(p.getContent())
                .userId(p.getUserId())
                .viewCount(p.getViewCount())
                .regDt(p.getRegDt())
                .uptDt(p.getUptDt())
                .build()).orElse(null);
    }

    public static Post dtoToEntity(PostDto dto) {
        return Optional.ofNullable(dto).map(p -> Post.builder()
                .postId(p.getPostId())
                .board(p.getBoard() != null ? BoardMapper.dtoToEntity(p.getBoard()) : null)
                .title(p.getTitle())
                .content(p.getContent())
                .userId(p.getUserId())
                .viewCount(p.getViewCount())
                .regDt(p.getRegDt())
                .uptDt(p.getUptDt())
                .build()).orElse(null);
    }
}