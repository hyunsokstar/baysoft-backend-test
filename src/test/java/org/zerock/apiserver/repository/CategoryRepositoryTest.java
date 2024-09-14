package org.zerock.apiserver.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.test.annotation.Rollback;
import org.zerock.apiserver.domain.Category;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Rollback(true)
    public void testCreateCategory() {
        // 기존 테스트 코드
        // ...
    }

    @Test
    @Rollback(true)
    public void testReadCategory() {
        // Create a category first
        Category category = categoryRepository.save(Category.builder()
                .name("Read Test Category")
                .regDt(LocalDateTime.now())
                .build());

        // Read the category
        Category foundCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Read Test Category");

        log.info("Read Category: " + foundCategory);
    }

    @Test
    @Rollback(true)
    public void testUpdateCategory() {
        // Create a category first
        Category category = categoryRepository.save(Category.builder()
                .name("Update Test Category")
                .regDt(LocalDateTime.now())
                .build());

        // Update the category
        category.setName("Updated Category");
        category.setUptDt(LocalDateTime.now());
        Category updatedCategory = categoryRepository.save(category);

        assertThat(updatedCategory.getName()).isEqualTo("Updated Category");
        assertThat(updatedCategory.getUptDt()).isNotNull();

        log.info("Updated Category: " + updatedCategory);
    }

    @Test
    @Rollback(true)
    public void testDeleteCategory() {
        // Create a category first
        Category category = categoryRepository.save(Category.builder()
                .name("Delete Test Category")
                .regDt(LocalDateTime.now())
                .build());

        // Delete the category
        categoryRepository.delete(category);

        // Try to find the deleted category
        Category deletedCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);
        assertThat(deletedCategory).isNull();

        log.info("Deleted Category ID: " + category.getCategoryId());
    }

    @Test
    @Rollback(true)
    public void testListCategories() {
        // Create multiple categories
        for (int i = 0; i < 5; i++) {
            categoryRepository.save(Category.builder()
                    .name("List Test Category " + i)
                    .regDt(LocalDateTime.now())
                    .build());
        }

        // List all categories
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).isNotEmpty();
        assertThat(categories.size()).isGreaterThanOrEqualTo(5);

        log.info("Total categories: " + categories.size());
        categories.forEach(cat -> log.info("Category: " + cat));
    }
}