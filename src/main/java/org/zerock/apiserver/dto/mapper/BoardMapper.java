package org.zerock.apiserver.dto.mapper;

import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.CategoryDto;

import java.util.Optional;

public class BoardMapper {
    public static BoardDto entityToDto(Board board) {
        return Optional.ofNullable(board).map(b -> BoardDto.builder()
                .boardId(b.getBoardId())
                .name(b.getName())
                .description(b.getDescription())
                .allowComments(b.getAllowComments())
                .commentLevel(b.getCommentLevel())
                .allowAttachments(b.getAllowAttachments())
                .isActive(b.getIsActive())
                .isPrivate(b.getIsPrivate())
                .adminOnlyWrite(b.getAdminOnlyWrite())
                .allowOnlyAdminOrAuthorComments(b.getAllowOnlyAdminOrAuthorComments())
                .regDt(b.getRegDt())
                .uptDt(b.getUptDt())
                .category(b.getCategory() != null ? CategoryMapper.entityToDto(b.getCategory()) : null)
                .build()).orElse(null);
    }

    public static Board dtoToEntity(BoardDto dto) {
        return Optional.ofNullable(dto).map(b -> Board.builder()
                .boardId(b.getBoardId())
                .name(b.getName())
                .description(b.getDescription())
                .allowComments(b.getAllowComments())
                .commentLevel(b.getCommentLevel())
                .allowAttachments(b.getAllowAttachments())
                .isActive(b.getIsActive())
                .isPrivate(b.getIsPrivate())
                .adminOnlyWrite(b.getAdminOnlyWrite())
                .allowOnlyAdminOrAuthorComments(b.getAllowOnlyAdminOrAuthorComments())
                .regDt(b.getRegDt())
                .uptDt(b.getUptDt())
                .category(b.getCategory() != null ? CategoryMapper.dtoToEntity(b.getCategory()) : null)
                .build()).orElse(null);
    }
}