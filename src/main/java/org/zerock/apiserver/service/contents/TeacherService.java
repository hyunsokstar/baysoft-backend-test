package org.zerock.apiserver.service.contents;

import org.zerock.apiserver.dto.PageResponseDTO;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.category.CategoryDto;
import org.zerock.apiserver.dto.contents.TeacherDto;

import java.util.List;

public interface TeacherService {
    PageResponseDtoMini<TeacherDto> findTeachersWithPaginationAndFilter(SearchRequestDTO searchRequestDTO);
    // getAllTeachersList 를 여기에 추가 단 TeacherDto 객체를 활용해 응답할 예정
    List<TeacherDto> getAllTeachersList();

}
