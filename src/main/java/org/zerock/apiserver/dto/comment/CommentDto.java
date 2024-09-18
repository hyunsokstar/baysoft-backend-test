package org.zerock.apiserver.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private Long postId;
    private Long parentCommentId;
    private String content;
    private Long userId;
    private LocalDateTime regDt;
    private LocalDateTime uptDt;
    private List<CommentDto> childComments;

    // 추가적인 필드나 메서드가 필요하다면 여기에 추가할 수 있습니다.
}