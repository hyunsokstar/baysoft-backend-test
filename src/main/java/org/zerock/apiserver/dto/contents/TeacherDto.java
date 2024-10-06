package org.zerock.apiserver.dto.contents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private Long teacherId;
    private String teacherName;
    private Boolean visibilityYn;
    private LocalDate registeredDate;
    private String managerCode;
}
