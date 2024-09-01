package org.zerock.apiserver.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testSearch1() {
        todoRepository.search1();
    }

    @Test
    public void testSomeMethod() {
        log.info("This is a test log message");
        log.debug("This is a debug message");
        log.warn("This is a warning message");
        log.error("This is an error message");
    }
}