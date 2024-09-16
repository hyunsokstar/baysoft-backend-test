//  src\main\java\org\zerock\apiserver\repository\TodoRepository.javas
package org.zerock.apiserver.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.repository.todo.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
    // 필요한 경우 추가적인 쿼리 메서드를 정의할 수 있습니다.
}
