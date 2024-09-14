package org.zerock.apiserver.service;

import org.zerock.apiserver.dto.category.CreateCategoryDto;

public interface CategoryService {
    Long createCategory(CreateCategoryDto createCategoryDto);
}