package org.zerock.apiserver.service;

import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.category.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    Long createCategory(CreateCategoryDto createCategoryDto);
    List<CategoryDto> getAllCategories();
    int removeCategories(List<Long> categoryIds);
}