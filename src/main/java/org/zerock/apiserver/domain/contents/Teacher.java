package org.zerock.apiserver.domain.contents;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "visibility_yn", nullable = false)
    private Boolean visibilityYn;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "manager_code")
    private String managerCode;

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", visibilityYn=" + visibilityYn +
                ", registeredDate=" + registeredDate +
                ", managerCode='" + managerCode + '\'' +
                '}';
    }
}