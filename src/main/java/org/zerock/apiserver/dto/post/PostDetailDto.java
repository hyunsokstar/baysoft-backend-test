package org.zerock.apiserver.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.apiserver.dto.comment.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailDto {
    private Long postId;
    private String title;
    private String content;
    private Long userId;
    private Integer viewCount;
    private LocalDateTime regDt;
    private LocalDateTime uptDt;
    private Long boardId;
    private String boardName;
    private List<CommentDto> comments;
}