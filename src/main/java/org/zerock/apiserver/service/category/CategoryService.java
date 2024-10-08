package org.zerock.apiserver.service.category;

import org.zerock.apiserver.dto.category.CategoryDto;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.category.CategoryDtoMini;
import org.zerock.apiserver.dto.category.CategoryOperationResult;
import org.zerock.apiserver.dto.category.CreateCategoryDto;

import java.util.List;

public interface CategoryService {

    int removeCategories(List<Long> categoryIds);
    Long createCategory(CreateCategoryDto createCategoryDto);
    PageResponseDTO<CategoryDto> getAllCategories(SearchRequestDTO searchRequestDTO);

    CategoryOperationResult saveOrUpdateCategories(List<CategoryDto> categoryDtoList);

    List<CategoryDtoMini> getCategoryIdAndNames();

}