package org.zerock.apiserver.repository.todo;

import org.springframework.data.domain.Page;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;

public interface TodoSearch {
    PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO);
}
