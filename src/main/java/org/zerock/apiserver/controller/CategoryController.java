package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.CategoryDto;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.service.CategoryService;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.info("Get all categories request");
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
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