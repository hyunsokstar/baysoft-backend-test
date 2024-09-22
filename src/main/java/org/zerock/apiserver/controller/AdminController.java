package org.zerock.apiserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/hello")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello, Admin!");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard!");
    }

    @GetMapping("/users")
    public ResponseEntity<String> listUsers() {
        // 실제 구현에서는 사용자 목록을 반환할 것입니다.
        return ResponseEntity.ok("Here's the list of all users (Admin only)");
    }

    @GetMapping("/system-status")
    public ResponseEntity<String> getSystemStatus() {
        // 실제 구현에서는 시스템 상태 정보를 반환할 것입니다.
        return ResponseEntity.ok("System is running smoothly (Admin only view)");
    }
}