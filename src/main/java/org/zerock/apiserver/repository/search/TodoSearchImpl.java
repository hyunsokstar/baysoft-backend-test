package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);  // 엔티티 클래스를 명시적으로 전달
    }


    //  pageRequestDto 는 검색과 관련된 정보를 모두 가지고 있음
    @Override
    public Page<Todo> search1(PageRequestDTO pageRequestDTO) {
        log.info("search1...........");
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        // 조건 추가
        query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        // 데이터 조회 및 페이징 결과 반환
        List<Todo> list = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(list, pageable, total);
    }
}
