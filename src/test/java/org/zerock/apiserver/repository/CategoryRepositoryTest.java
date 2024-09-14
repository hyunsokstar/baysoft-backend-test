package org.zerock.apiserver.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.test.annotation.Rollback;
import org.zerock.apiserver.domain.Category;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Rollback(true) // 테스트 후 데이터 롤백
    public void testCreateCategory() {
        // 새로운 Category 객체 생성
        Category category = Category.builder()
                .name("Test Category")
                .regDt(LocalDateTime.now())
                .build();

        // 카테고리 저장
        Category savedCategory = categoryRepository.save(category);

        // 로그로 저장된 카테고리 정보 출력
        log.info("Saved Category: " + savedCategory);

        // 저장된 카테고리 검증
        assertThat(savedCategory.getCategoryId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Test Category");
        assertThat(savedCategory.getRegDt()).isNotNull();

        // 저장된 카테고리 조회
        Category foundCategory = categoryRepository.findById(savedCategory.getCategoryId()).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Test Category");

        // 저장된 카테고리 수 확인
        long count = categoryRepository.count();
        log.info("Total categories: " + count);
        assertThat(count).isGreaterThan(0);
    }
}