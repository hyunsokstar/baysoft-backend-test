package org.zerock.apiserver.dto.post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.apiserver.dto.board.BoardDto;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long postId;
    private BoardDto board;
    private String title;
    private String content;
    private Long userId;
    private Integer viewCount;
    private LocalDate regDt;
    private LocalDate uptDt;
}