package org.zerock.apiserver.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.repository.board.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        return boardRepository.search(searchRequestDTO);
    }
}