package org.zerock.apiserver.service.contents;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.apiserver.domain.contents.Teacher;
import org.zerock.apiserver.dto.PageResponseDtoMini;
import org.zerock.apiserver.dto.SearchRequestDTO;
import org.zerock.apiserver.dto.contents.TeacherDto;
import org.zerock.apiserver.dto.mapper.TeacherMapper;
import org.zerock.apiserver.repository.contents.teacher.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public PageResponseDtoMini<TeacherDto> findTeachersWithPaginationAndFilter(SearchRequestDTO searchRequestDTO) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDto> getAllTeachersList() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(TeacherMapper::toDto)
                .collect(Collectors.toList());
    }

    // other methods...
}