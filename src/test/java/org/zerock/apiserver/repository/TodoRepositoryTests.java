package org.zerock.apiserver.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        // 새로운 Todo 객체를 생성합니다.
        Todo todo = Todo.builder()
                .title("Spring Data JPA Test")
                .content("스프링 데이터 JPA 테스트 중입니다.")
                .dueDate(LocalDate.of(2023, 12, 31))
                .build();

        // 생성한 Todo 객체를 저장합니다.
        Todo savedTodo = todoRepository.save(todo);

        // 저장된 Todo 객체의 tno(ID)가 null이 아닌지 확인합니다.
        assertNotNull(savedTodo.getTno());
    }

    @Test
    public void testRead() {
        // 먼저 데이터를 저장합니다.
        Todo todo = Todo.builder()
                .title("Read Test")
                .content("읽기 테스트")
                .dueDate(LocalDate.now())
                .build();
        Todo savedTodo = todoRepository.save(todo);

        // 저장된 데이터를 ID로 조회합니다.
        Optional<Todo> result = todoRepository.findById(savedTodo.getTno());

        // 조회된 데이터가 존재하는지 확인합니다.
        assertTrue(result.isPresent());
        // 조회된 데이터의 제목이 저장할 때의 제목과 일치하는지 확인합니다.
        assertEquals("Read Test", result.get().getTitle());
    }

    @Test
    public void testUpdate() {
        // 먼저 데이터를 저장합니다.
        Todo todo = Todo.builder()
                .title("Update Test")
                .content("업데이트 테스트")
                .dueDate(LocalDate.now())
                .build();
        Todo savedTodo = todoRepository.save(todo);

        // 저장된 데이터의 제목을 변경합니다.
        savedTodo.changeTitle("Updated Title");
        Todo updatedTodo = todoRepository.save(savedTodo);

        // 변경된 제목이 정확히 저장되었는지 확인합니다.
        assertEquals("Updated Title", updatedTodo.getTitle());
    }

    @Test
    public void testDelete() {
        // 먼저 데이터를 저장합니다.
        Todo todo = Todo.builder()
                .title("Delete Test")
                .content("삭제 테스트")
                .dueDate(LocalDate.now())
                .build();
        Todo savedTodo = todoRepository.save(todo);

        // 저장된 데이터를 삭제합니다.
        todoRepository.delete(savedTodo);

        // 삭제된 데이터를 조회하여 존재하지 않는지 확인합니다.
        Optional<Todo> result = todoRepository.findById(savedTodo.getTno());
        assertFalse(result.isPresent());
    }

}