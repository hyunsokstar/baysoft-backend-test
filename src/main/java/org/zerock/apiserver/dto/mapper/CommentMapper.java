package org.zerock.apiserver.dto.mapper;

import org.zerock.apiserver.domain.Comment;
import org.zerock.apiserver.dto.comment.CommentDto;

import java.util.Optional;
import java.util.stream.Collectors;

public class CommentMapper {

    public static CommentDto entityToDto(Comment comment) {
        return Optional.ofNullable(comment).map(c -> CommentDto.builder()
                .commentId(c.getCommentId())
                .postId(c.getPost().getPostId())
                .parentCommentId(c.getParentComment() != null ? c.getParentComment().getCommentId() : null)
                .content(c.getContent())
                .userId(c.getUserId())
                .regDt(c.getRegDt())
                .uptDt(c.getUptDt())
                .childComments(c.getChildComments() != null ?
                        c.getChildComments().stream()
                                .map(CommentMapper::entityToDto)
                                .collect(Collectors.toList())
                        : null)
                .build()).orElse(null);
    }

    public static Comment dtoToEntity(CommentDto dto) {
        return Optional.ofNullable(dto).map(d -> Comment.builder()
                .commentId(d.getCommentId())
                .content(d.getContent())
                .userId(d.getUserId())
                .regDt(d.getRegDt())
                .uptDt(d.getUptDt())
                .build()).orElse(null);
    }
}