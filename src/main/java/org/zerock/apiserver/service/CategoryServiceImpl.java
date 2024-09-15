package org.zerock.apiserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .regDt(category.getRegDt())
                .uptDt(category.getUptDt())
                .build();
    }

    @Override
    public Long createCategory(CreateCategoryDto createCategoryDto) {
        Category category = Category.builder()
                .name(createCategoryDto.getName())
                .regDt(LocalDateTime.now())
                .build();

        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getCategoryId();
    }

    @Override
    @Transactional
    public int removeCategories(List<Long> categoryIds) {
        int deletedCount = 0;
        for (Long categoryId : categoryIds) {
            if (categoryRepository.existsById(categoryId)) {
                categoryRepository.deleteById(categoryId);
                deletedCount++;
            } else {
                log.warn("삭제하려는 카테고리가 존재하지 않습니다: CategoryId = " + categoryId);
            }
        }
        return deletedCount;
    }

}