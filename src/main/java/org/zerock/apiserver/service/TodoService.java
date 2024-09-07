package org.zerock.apiserver.service;

import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.TodoDTO;

public interface TodoService {
    TodoDTO get(long tno);

    Long register(TodoDTO dto);

    void modify(TodoDTO dto);

    void remove(Long tno);

    // 페이지 타입을 리턴 하는 getList 함수
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    default TodoDTO entityToDto(Todo todo) {
        TodoDTO todoDto =
                TodoDTO.builder()
                        .tno(todo.getTno())
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .complete(todo.isComplete())
                        .dueDate(todo.getDueDate())
                        .build();
        return todoDto;
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

}
