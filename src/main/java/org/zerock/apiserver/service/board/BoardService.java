package org.zerock.apiserver.service.board;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;

public interface BoardService {
    PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO);
}