package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);  // 엔티티 클래스를 명시적으로 전달
    }

    @Override
    public Page<TodoDTO> search1() {  // 반환 타입을 Page<TodoDTO>로 변경

        log.info("search1...........");
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        // 조건 추가
        query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        // 데이터 조회 및 페이징 결과 반환
        List<Todo> result = query.fetch();
        long count = query.fetchCount();

        // Todo -> TodoDTO 변환
        List<TodoDTO> dtoList = result.stream()
                .map(entity -> new TodoDTO(entity.getTno(), entity.getTitle(), entity.getContent()))
                .collect(Collectors.toList());

        // PageImpl<TodoDTO>를 반환
        return new PageImpl<>(dtoList, pageable, count);
    }
}
