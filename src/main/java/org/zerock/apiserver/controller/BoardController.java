package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.BoardOperationResult;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.service.board.BoardService;

import java.util.List;

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

    @PostMapping("/save-or-update")
    public ResponseEntity<BoardOperationResult> saveOrUpdateBoards(@RequestBody List<CreateBoardDto> boardDtoList) {
        BoardOperationResult result = boardService.saveOrUpdateBoards(boardDtoList);
        return ResponseEntity.ok(result);
    }

}