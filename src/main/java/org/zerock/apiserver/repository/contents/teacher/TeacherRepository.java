package org.zerock.apiserver.repository.contents.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.contents.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // 필요한 커스텀 쿼리 메서드를 정의할 수도 있습니다.
}
