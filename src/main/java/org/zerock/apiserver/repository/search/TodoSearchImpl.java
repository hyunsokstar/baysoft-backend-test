package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);  // 엔티티 클래스를 명시적으로 전달
    }

    @Override
    public Page<Todo> search1() {

        log.info("search1...........");
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        // 조건 추가
        query.where(todo.title.contains("1"));

        // 페이징 처리
        Pageable pageable = PageRequest.of(0, 10);  // 페이지 번호와 크기 설정 (0 페이지, 10개 항목)
        this.getQuerydsl().applyPagination(pageable, query);

        // 데이터 조회 및 페이징 결과 반환
        List<Todo> result = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(result, pageable, count);
    }
}
