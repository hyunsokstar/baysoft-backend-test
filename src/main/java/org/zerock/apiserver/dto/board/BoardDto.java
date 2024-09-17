package org.zerock.apiserver.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.apiserver.dto.category.CategoryDto;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
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
    private LocalDateTime regDt;
    private LocalDateTime uptDt;
    private CategoryDto category;

}