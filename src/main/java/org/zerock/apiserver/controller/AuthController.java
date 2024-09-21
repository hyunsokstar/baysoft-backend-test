package org.zerock.apiserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.auth.RefreshTokenRequest;
import org.zerock.apiserver.dto.auth.TokenResponse;
import org.zerock.apiserver.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (jwtUtil.validateToken(request.getRefreshToken())) {
            String username = jwtUtil.getUsernameFromToken(request.getRefreshToken());
            String newAccessToken = jwtUtil.generateAccessToken(username);
            return ResponseEntity.ok(new TokenResponse(newAccessToken));
        }
        return ResponseEntity.badRequest().body("Invalid refresh token");
    }
}