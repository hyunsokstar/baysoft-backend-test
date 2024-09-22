package org.zerock.apiserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.auth.RefreshTokenRequest;
import org.zerock.apiserver.dto.auth.TokenResponse;
import org.zerock.apiserver.dto.auth.UserInfoResponse;
import org.zerock.apiserver.security.CustomUserDetails;
import org.zerock.apiserver.security.JwtUtil;
import org.zerock.apiserver.domain.auth.User;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();

            UserInfoResponse response = new UserInfoResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setRoles(user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toSet()));

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("User not authenticated");
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