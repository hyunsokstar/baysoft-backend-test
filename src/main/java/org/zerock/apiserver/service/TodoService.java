package org.zerock.apiserver.service;

import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO get(long tno);

    Long register(TodoDTO dto);

    void modify(TodoDTO dto);

    void remove(Long tno);

    // 여러 개의 Todo를 한 번에 삭제
    void removeTodos(List<Long> tnoList);

    void registerTodos(List<TodoDTO> todoDTOList); // 리스트 저장 및 업데이트

    PageResponseDTO<TodoDTO> getList(SearchRequestDTO searchRequestDTO);

    default TodoDTO entityToDto(Todo todo) {
        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();
    }

    default Todo dtoToEntity(TodoDTO todoDto) {
        return Todo.builder()
                .tno(todoDto.getTno())
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate())
                .build();
    }
    PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO);

}