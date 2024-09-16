package org.zerock.apiserver.service.board;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.CreateBoardDto;

public interface BoardService {
    PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO);
    BoardDto createBoard(CreateBoardDto createBoardDto);
}