package org.zerock.apiserver.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PageRequestDTO;
import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.TodoDTO;

public interface TodoSearch {
//    Page<Todo> search1(PageRequestDTO pageRequestDTO);
    PageResponseDTO<TodoDTO> search(SearchRequestDTO searchRequestDTO);

    Object entityToDto(Todo todo);
}
