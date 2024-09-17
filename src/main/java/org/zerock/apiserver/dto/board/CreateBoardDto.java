package org.zerock.apiserver.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardDto {
    private Long boardId;
    private String name;
    private String description;
    private Boolean allowComments;
    private Integer commentLevel;
    private Boolean allowAttachments;
    private Boolean isActive;
    private Boolean isPrivate;
    private Boolean adminOnlyWrite;
    private Boolean allowOnlyAdminOrAuthorComments;
    private Long categoryId;
    private LocalDateTime regDt;
    private LocalDateTime uptDt;
}