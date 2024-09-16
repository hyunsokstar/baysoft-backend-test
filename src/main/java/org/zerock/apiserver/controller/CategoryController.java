package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.category.CategoryOperationResult;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.service.category.CategoryService;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<CategoryDto>> getAllCategories(SearchRequestDTO searchRequestDTO) {
        log.info("Get all categories request with search: {}", searchRequestDTO);
        PageResponseDTO<CategoryDto> result = categoryService.getAllCategories(searchRequestDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/multiple")
    public ResponseEntity<Map<String, Object>> saveOrUpdateCategories(@RequestBody List<CategoryDto> categoryDtoList) {
        log.info("Batch saveOrUpdateCategories request: {}", categoryDtoList);
        CategoryOperationResult result = categoryService.saveOrUpdateCategories(categoryDtoList);

        return ResponseEntity.ok(Map.of(
                "RESULT", "SUCCESS",
                "UPDATED_COUNT", result.getUpdatedCount(),
                "CREATED_COUNT", result.getCreatedCount(),
                "MESSAGE", String.format("%d개의 카테고리가 업데이트되고, %d개의 카테고리가 새로 생성되었습니다.",
                        result.getUpdatedCount(), result.getCreatedCount())
        ));
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        log.info("Create category request: " + createCategoryDto);
        Long categoryId = categoryService.createCategory(createCategoryDto);
        return ResponseEntity.ok(Map.of("CATEGORY_ID", categoryId));
    }

    @DeleteMapping("/multiple")
    public ResponseEntity<Map<String, Object>> removeCategories(@RequestBody Map<String, List<Long>> request) {
        List<Long> categoryIds = request.get("categoryIds");
        log.info("Batch remove categories request: {}", categoryIds);
        int deletedCount = categoryService.removeCategories(categoryIds);
        return ResponseEntity.ok(Map.of(
                "RESULT", "SUCCESS",
                "DELETED_COUNT", deletedCount,
                "MESSAGE", deletedCount + "개의 카테고리가 삭제되었습니다."
        ));
    }
}