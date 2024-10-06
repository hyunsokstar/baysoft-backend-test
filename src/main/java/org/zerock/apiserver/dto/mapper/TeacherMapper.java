package org.zerock.apiserver.dto.mapper;

import org.zerock.apiserver.domain.contents.Teacher;
import org.zerock.apiserver.dto.contents.TeacherDto;

public class TeacherMapper {

    public static TeacherDto toDto(Teacher teacher) {
        return TeacherDto.builder()
                .teacherId(teacher.getTeacherId())
                .teacherName(teacher.getTeacherName())
                .visibilityYn(teacher.getVisibilityYn())
                .registeredDate(teacher.getRegisteredDate())
                .managerCode(teacher.getManagerCode())
                .build();
    }

    public static Teacher toEntity(TeacherDto teacherDto) {
        return Teacher.builder()
                .teacherName(teacherDto.getTeacherName())
                .visibilityYn(teacherDto.getVisibilityYn())
                .registeredDate(teacherDto.getRegisteredDate())
                .managerCode(teacherDto.getManagerCode())
                .build();
    }
}