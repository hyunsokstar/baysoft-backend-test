package org.zerock.apiserver.dto.mapper;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

public class TodoMapper {

    public static TodoDTO entityToDto(Todo todo) {
        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();
    }

    public static Todo dtoToEntity(TodoDTO dto) {
        return Todo.builder()
                .tno(dto.getTno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .complete(dto.isComplete())
                .dueDate(dto.getDueDate())
                .build();
    }
}
