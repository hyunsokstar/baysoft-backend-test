package org.zerock.apiserver.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsertTodo() {
        // Given: 새로운 Todo 생성
        Todo todo = Todo.builder()
                .title("Test Title")
                .content("Test Content")
                .complete(false)
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        // When: Todo 저장
        Todo savedTodo = todoRepository.save(todo);

        // Then: 저장된 Todo가 null이 아닌지 확인
        assertNotNull(savedTodo.getTno(), "Todo가 성공적으로 저장되었습니다.");

        // 로그 출력
        log.info("새로 생성된 Todo: {}", savedTodo);

        // 저장된 Todo를 다시 조회하여 확인
        Optional<Todo> retrievedTodo = todoRepository.findById(savedTodo.getTno());
        assertTrue(retrievedTodo.isPresent(), "저장된 Todo를 성공적으로 조회하였습니다.");
        assertEquals("Test Title", retrievedTodo.get().getTitle(), "제목이 일치합니다.");
        assertEquals("Test Content", retrievedTodo.get().getContent(), "내용이 일치합니다.");

        log.info("조회된 Todo: {}", retrievedTodo.get());
    }

    @Test
    public void testUpdateTodo() {
        // Given: 새로운 Todo 생성 및 저장
        Todo todo = Todo.builder()
                .title("Original Title")
                .content("Original Content")
                .complete(false)
                .dueDate(LocalDate.now().plusDays(7))
                .build();
        Todo savedTodo = todoRepository.save(todo);

        // When: Todo의 제목과 내용을 수정
        savedTodo.changeTitle("Updated Title");
        savedTodo.changeContent("Updated Content");
        Todo updatedTodo = todoRepository.save(savedTodo);

        // Then: 수정된 내용 확인
        Optional<Todo> retrievedTodo = todoRepository.findById(updatedTodo.getTno());
        assertTrue(retrievedTodo.isPresent(), "수정된 Todo를 성공적으로 조회하였습니다.");
        assertEquals("Updated Title", retrievedTodo.get().getTitle(), "수정된 제목이 일치합니다.");
        assertEquals("Updated Content", retrievedTodo.get().getContent(), "수정된 내용이 일치합니다.");

        // 로그 출력
        log.info("수정된 Todo: {}", updatedTodo);
    }
}
