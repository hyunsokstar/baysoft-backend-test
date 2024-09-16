package org.zerock.apiserver.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardDto {
    private String name;
    private String description;
    private Boolean allowComments;
    private Integer commentLevel;
    private Boolean allowAttachments;
    private Boolean isActive;
    private Boolean isPrivate;
    private Boolean adminOnlyWrite;
    private Boolean allowOnlyAdminOrAuthorComments;

    // 카테고리 ID 추가
    private Long categoryId;  // Board 생성 시 어떤 카테고리에 속할지 지정
}
