package org.zerock.apiserver.service.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.category.CategoryOperationResult;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.repository.category.CategoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public PageResponseDTO<CategoryDto> getAllCategories(SearchRequestDTO searchRequestDTO) {
        // 레포지토리의 search 메소드를 사용하여 검색 및 페이지네이션 처리
        return categoryRepository.search(searchRequestDTO);
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
                .regDt(LocalDate.now())
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


    @Override
    @Transactional
    public CategoryOperationResult saveOrUpdateCategories(List<CategoryDto> categoryDtoList) {
        int updatedCount = 0;
        int createdCount = 0;

        for (CategoryDto dto : categoryDtoList) {
            if (dto.getCategoryId() != null && categoryRepository.existsById(dto.getCategoryId())) {
                updateCategory(dto);
                updatedCount++;
            } else {
                createCategory(dto);
                createdCount++;
            }
        }

        return new CategoryOperationResult(updatedCount, createdCount);
    }

    private void updateCategory(CategoryDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));

        category.setName(dto.getName());
        category.setUptDt(LocalDate.now());

        categoryRepository.save(category);
        log.info("Category updated: {}", category);
    }

    private void createCategory(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .regDt(LocalDate.now())
                .build();

        Category savedCategory = categoryRepository.save(category);
        log.info("New category created: {}", savedCategory);
    }

}