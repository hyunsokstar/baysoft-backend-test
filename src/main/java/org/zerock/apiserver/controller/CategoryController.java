package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.category.CreateCategoryDto;
import org.zerock.apiserver.service.CategoryService;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        log.info("Create category request: " + createCategoryDto);
        Long categoryId = categoryService.createCategory(createCategoryDto);
        return ResponseEntity.ok(Map.of("CATEGORY_ID", categoryId));
    }
}