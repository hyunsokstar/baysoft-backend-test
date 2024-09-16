package org.zerock.apiserver.repository.category;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.domain.QCategory;
import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchImpl extends QuerydslRepositorySupport implements CategorySearch {

    public CategorySearchImpl() {
        super(Category.class);
    }

    @Override
    public PageResponseDTO<CategoryDto> search(SearchRequestDTO searchRequestDTO) {
        BooleanBuilder builder = getSearchConditions(searchRequestDTO);
        QCategory category = QCategory.category;

        JPQLQuery<Category> query = from(category)
                .where(builder);

        Pageable pageable = getPageable(searchRequestDTO);
        this.getQuerydsl().applyPagination(pageable, query);

        List<CategoryDto> dtoList = query.fetch().stream()
                .map(CategoryMapper::entityToDto)
                .collect(Collectors.toList());

        long total = query.fetchCount();

        return PageResponseDTO.<CategoryDto>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(searchRequestDTO)
                .total(total)
                .build();
    }

    private BooleanBuilder getSearchConditions(SearchRequestDTO searchRequestDTO) {
        BooleanBuilder builder = new BooleanBuilder();
        QCategory category = QCategory.category;

        String keyword = searchRequestDTO.getSearchKeyword();
        if (keyword != null && !keyword.isEmpty()) {
            switch (searchRequestDTO.getSearchType()) {
                case "name":
                    builder.and(category.name.contains(keyword));
                    break;
                default:
                    builder.and(category.name.contains(keyword));
            }
        }
        return builder;
    }

    private Pageable getPageable(SearchRequestDTO searchRequestDTO) {
        return PageRequest.of(
                searchRequestDTO.getPage() - 1,
                searchRequestDTO.getSize(),
                Sort.by("categoryId").descending()
        );
    }
}
