package org.zerock.apiserver.repository.board;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;

public interface BoardSearch {
    PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO);
}