package org.zerock.apiserver.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.apiserver.dto.TodoDTO;

public interface TodoSearch {
    Page<TodoDTO> search1();
}
