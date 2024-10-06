package org.zerock.apiserver.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.apiserver.domain.contents.Teacher;
import org.zerock.apiserver.repository.contents.teacher.TeacherRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TeacherDataInitializer implements CommandLineRunner {

    private final TeacherRepository teacherRepository;
    private Random random = new Random();

    public TeacherDataInitializer(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (teacherRepository.count() == 0) {
            initializeTeacherData();
        }
    }

    private void initializeTeacherData() {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Teacher teacher = Teacher.builder()
                    .teacherName("Teacher " + i)
                    .visibilityYn(random.nextBoolean())
                    .registeredDate(LocalDate.now().minusDays(random.nextInt(365)))
                    .managerCode("MGR" + String.format("%03d", i))
                    .build();
            teachers.add(teacher);
        }
        teacherRepository.saveAll(teachers);
        System.out.println("10 Teacher records have been initialized.");
    }
}