package org.zerock.apiserver.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);  // 엔티티 클래스를 명시적으로 전달
    }

    @Override
    public PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO) {
        log.info("search with SearchRequestDTO...........");

        QTodo todo = QTodo.todo;
        JPQLQuery<Todo> query = from(todo);

        // 검색 조건 추가
        BooleanBuilder builder = new BooleanBuilder();

        if (searchRequestDTO.getSearchKeyword() != null && !searchRequestDTO.getSearchKeyword().isEmpty()) {
            if ("title".equals(searchRequestDTO.getSearchType())) {
                builder.and(todo.title.contains(searchRequestDTO.getSearchKeyword()));
            } else if ("content".equals(searchRequestDTO.getSearchType())) {
                builder.and(todo.content.contains(searchRequestDTO.getSearchKeyword()));
            } else {
                // 기본적으로 제목과 내용 모두 검색
                builder.and(todo.title.contains(searchRequestDTO.getSearchKeyword())
                        .or(todo.content.contains(searchRequestDTO.getSearchKeyword())));
            }
        }

        query.where(builder);

        Pageable pageable = PageRequest.of(
                searchRequestDTO.getPage() - 1,
                searchRequestDTO.getSize(),
                Sort.by("tno").descending());

        // 페이징 적용
        this.getQuerydsl().applyPagination(pageable, query);

        // 쿼리 실행 및 결과 반환
        List<Todo> list = query.fetch();
        long total = query.fetchCount();

        // Todo 엔티티를 TodoDTO로 변환
        List<TodoDTO> dtoList = list.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(searchRequestDTO)
                .total(total)
                .build();
    }

    public TodoDTO entityToDto(Todo todo) {
        return TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();
    }
}