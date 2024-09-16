package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.service.board.BoardService;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/search")
    public ResponseEntity<PageResponseDtoMini<BoardDto>> searchBoards(SearchRequestDTO searchRequestDTO) {
        PageResponseDtoMini<BoardDto> result = boardService.search(searchRequestDTO);
        return ResponseEntity.ok(result);
    }
}