package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;
import org.zerock.apiserver.service.TodoService;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno) {
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(SearchRequestDTO searchRequestDTO) {
        log.info("searchRequestDTO......for list !" + searchRequestDTO);
        return todoService.getList(searchRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO dto) {
        log.info("todoDto : " + dto);
        Long tno = todoService.register(dto);
        return Map.of("TNO", tno);
    }

    @PostMapping("/batch")
    public Map<String, String> saveOrUpdateTodos(@RequestBody List<TodoDTO> todoDTOList) {
        log.info("Batch saveOrUpdateTodos 요청 확인 !! ");
        todoService.registerTodos(todoDTOList);
        return Map.of("RESULT", "SUCCESS");
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO) {
        log.info("todoDto update 요청 확인 !! ", todoDTO);
        todoDTO.setTno(tno);
        todoService.modify(todoDTO);
        return Map.of("RESULT", "SUCCESS");
    }

    // 여러 개의 Todo 항목을 한 번에 삭제하는 API
    @DeleteMapping("/batch")
    public Map<String, String> removeTodos(@RequestBody List<Long> tnoList) {
        log.info("Batch remove 요청 확인 !! ", tnoList);
        todoService.removeTodos(tnoList);
        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno) {
        todoService.remove(tno);
        return Map.of("RESULT", "SUCCESS");
    }

    @GetMapping("/search")
    public PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO) {
        log.info("search......" + searchRequestDTO);
        return todoService.search(searchRequestDTO);
    }
}
