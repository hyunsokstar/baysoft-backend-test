package org.zerock.apiserver.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    private Long postId;  // 게시글 ID (생성 시에는 null, 수정 시에 사용)
    private Long boardId; // 게시판 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private Long userId; // 작성자 ID
    private Integer viewCount; // 조회수
    private LocalDate regDt; // 등록일시
    private LocalDate uptDt; // 수정일시
}