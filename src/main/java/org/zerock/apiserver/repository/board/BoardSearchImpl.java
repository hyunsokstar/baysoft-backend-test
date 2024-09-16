package org.zerock.apiserver.repository.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.Board;
import org.zerock.apiserver.domain.QBoard;
import org.zerock.apiserver.domain.QCategory;
import org.zerock.apiserver.dto.board.BoardDto;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.mapper.BoardMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public PageResponseDtoMini<BoardDto> search(SearchRequestDTO searchRequestDTO) {
        BooleanBuilder builder = getSearchConditions(searchRequestDTO);
        QBoard board = QBoard.board;
        QCategory category = QCategory.category;

        JPQLQuery<Board> query = from(board)
                .leftJoin(board.category, category).fetchJoin()
                .where(builder);
//        JPQLQuery<Board> query = from(board)
//                .where(builder);

        Pageable pageable = getPageable(searchRequestDTO);
        this.getQuerydsl().applyPagination(pageable, query);

        List<BoardDto> dtoList = query.fetch().stream()
                .map(BoardMapper::entityToDto)
                .collect(Collectors.toList());

        long total = query.fetchCount();

        return PageResponseDtoMini.<BoardDto>builder()
                .content(dtoList)
                .totalCount((int) total)
                .currentPage(searchRequestDTO.getPage())
                .build();
    }

    private BooleanBuilder getSearchConditions(SearchRequestDTO searchRequestDTO) {
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = QBoard.board;

        String keyword = searchRequestDTO.getSearchKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            switch (searchRequestDTO.getSearchType()) {
                case "name":
                    builder.and(board.name.contains(keyword));
                    break;
                case "description":
                    builder.and(board.description.contains(keyword));
                    break;
                default:
                    builder.and(board.name.contains(keyword).or(board.description.contains(keyword)));
            }
        }
        return builder;
    }

    private Pageable getPageable(SearchRequestDTO searchRequestDTO) {
        return PageRequest.of(
                searchRequestDTO.getPage() - 1,
                searchRequestDTO.getSize(),
                Sort.by("boardId").descending()
        );
    }
}