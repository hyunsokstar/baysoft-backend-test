package org.zerock.apiserver.repository.category;

import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.CategoryDto;

public interface CategorySearch {
    PageResponseDTO<CategoryDto> search(SearchRequestDTO searchRequestDTO);
}
