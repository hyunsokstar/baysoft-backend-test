package org.zerock.apiserver.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    public void setup() {
        // 테스트 데이터 삽입
        Todo todo1 = Todo.builder().title("Test Todo 1").content("Content 1").build();
        Todo todo2 = Todo.builder().title("Sample Todo 2").content("Content 2").build();
        Todo todo3 = Todo.builder().title("Another Test Todo 1").content("Content 3").build();

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
    }

    @Test
    @Rollback(true) // 테스트 후 데이터 롤백
    public void testSearch1() {
        Page<TodoDTO> result = todoRepository.search1();

        // 검색 결과 전체를 로그로 출력
        log.info("검색 결과 result : " + result);

        // 로그로 데이터 확인
        log.info("Total Elements: " + result.getTotalElements());
        log.info("Total Pages: " + result.getTotalPages());

        // 결과에 대한 검증
        assertThat(result.getTotalElements()).isGreaterThan(0);
        assertThat(result.getContent().get(0).getTitle()).contains("1");

        // 검색된 결과의 개수 출력
        log.info("Number of search results: " + result.getNumberOfElements());
    }


}