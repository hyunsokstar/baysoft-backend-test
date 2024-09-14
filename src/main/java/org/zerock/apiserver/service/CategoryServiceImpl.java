package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.repository.CategoryRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Long createCategory(CreateCategoryDto createCategoryDto) {
        Category category = Category.builder()
                .name(createCategoryDto.getName())
                .regDt(LocalDateTime.now())
                .build();

        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getCategoryId();
    }
}