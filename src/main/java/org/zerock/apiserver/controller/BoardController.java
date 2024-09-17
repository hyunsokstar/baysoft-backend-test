package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.board.BoardOperationResult;
import org.zerock.apiserver.dto.board.CreateBoardDto;
import org.zerock.apiserver.service.board.BoardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Log4j2
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

    @DeleteMapping("/multiple")
    public ResponseEntity<Map<String, Object>> removeBoards(@RequestBody Map<String, List<Long>> request) {
        List<Long> boardIds = request.get("boardIds");
        log.info("Batch remove boards request: {}", boardIds);
        int deletedCount = boardService.removeBoards(boardIds);
        return ResponseEntity.ok(Map.of(
                "RESULT", "SUCCESS",
                "DELETED_COUNT", deletedCount,
                "MESSAGE", deletedCount + "개의 게시판이 삭제되었습니다."
        ));
    }

}