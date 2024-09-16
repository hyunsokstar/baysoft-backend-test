package org.zerock.apiserver.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.dto.mapper.BoardMapper;
import org.zerock.apiserver.repository.board.BoardRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        return boardRepository.search(searchRequestDTO);
    }

    @Override
    public BoardDto createBoard(CreateBoardDto createBoardDto) {
        Board board = Board.builder()
                .name(createBoardDto.getName())
                .description(createBoardDto.getDescription())
                .allowComments(createBoardDto.getAllowComments())
                .commentLevel(createBoardDto.getCommentLevel())
                .allowAttachments(createBoardDto.getAllowAttachments())
                .isActive(createBoardDto.getIsActive())
                .isPrivate(createBoardDto.getIsPrivate())
                .adminOnlyWrite(createBoardDto.getAdminOnlyWrite())
                .allowOnlyAdminOrAuthorComments(createBoardDto.getAllowOnlyAdminOrAuthorComments())
                .regDt(LocalDateTime.now())
                .build();

        Board savedBoard = boardRepository.save(board);
        return BoardMapper.entityToDto(savedBoard);
    }
}