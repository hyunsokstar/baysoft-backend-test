package org.zerock.apiserver.controller.contents;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.dto.contents.TeacherDto;
import org.zerock.apiserver.service.contents.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    // 모든 선생님 리스트 가져오기
    // 모든 선생님 리스트 가져오기 (모든 사용자 접근 가능)
    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TeacherDto>> getAllTeachersPublic() {
//        @PreAuthorize("hasRole('ADMIN')")
        System.out.println("선생님 리스트 요청 확인");
        List<TeacherDto> teachers = teacherService.getAllTeachersList();
        return ResponseEntity.ok(teachers);
    }
}
