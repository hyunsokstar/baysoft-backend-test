package org.zerock.apiserver.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Category;
import org.zerock.apiserver.repository.category.CategorySearch;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategorySearch {
    // 추가적인 쿼리 메서드가 필요하다면 정의합니다.
}
