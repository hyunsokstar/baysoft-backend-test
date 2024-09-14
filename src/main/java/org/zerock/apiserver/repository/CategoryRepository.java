package org.zerock.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 필요한 경우 추가적인 쿼리 메서드를 정의할 수 있습니다.
}