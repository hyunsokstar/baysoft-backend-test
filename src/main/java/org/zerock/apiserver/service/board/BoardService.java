package org.zerock.apiserver.service.board;

import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.BoardOperationResult;
import org.zerock.apiserver.dto.board.CreateBoardDto;

import java.util.List;

public interface BoardService {
    PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO);
    //  BoardDto createBoard(CreateBoardDto createBoardDto);

    BoardOperationResult saveOrUpdateBoards(List<CreateBoardDto> boardDtoList);

    int removeBoards(List<Long> boardIds);  // 새로 추가된 메서드

}