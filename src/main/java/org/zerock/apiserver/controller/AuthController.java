package org.zerock.apiserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.auth.*;
import org.zerock.apiserver.security.CustomUserDetails;
import org.zerock.apiserver.security.JwtUtil;
import org.zerock.apiserver.domain.auth.User;
import lombok.extern.log4j.Log4j2;
import org.zerock.apiserver.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request received for user: {}", loginRequest.getUsername());
        try {
            log.debug("Attempting authentication for user: {}", loginRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            log.debug("Authentication successful for user: {}", loginRequest.getUsername());

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            String role = user.getRoles().iterator().next().getName();

            log.debug("Generating tokens for user: {}", user.getUsername());
            String accessToken = jwtUtil.generateAccessToken(user.getUsername(), role);
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            UserDTO userDTO = UserDTO.fromUser(user);

            LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken, userDTO);
            log.info("Login successful for user: {}", user.getUsername());
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", loginRequest.getUsername(), e);
            return ResponseEntity.badRequest().body(new LoginResponse(null, null, null));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        log.info("Request to get current user information");
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            UserDTO userDTO = UserDTO.fromUser(user);
            log.info("Current user information retrieved for: {}", user.getUsername());
            return ResponseEntity.ok(userDTO);
        }
        log.warn("User not authenticated");
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        log.info("Token refresh request received");
        if (jwtUtil.validateToken(request.getRefreshToken())) {
            String username = jwtUtil.getUsernameFromToken(request.getRefreshToken());
            log.debug("Refresh token valid for user: {}", username);
            User user = userService.findByUsername(username);
            if (user != null) {
                String role = user.getRoles().iterator().next().getName();
                String newAccessToken = jwtUtil.generateAccessToken(username, role);
                UserDTO userDTO = UserDTO.fromUser(user);
                log.info("Access token refreshed for user: {}", username);
                return ResponseEntity.ok(new TokenResponse(newAccessToken, request.getRefreshToken(), userDTO));
            }
        }
        log.warn("Invalid refresh token");
        return ResponseEntity.badRequest().body(new TokenResponse(null, null, null));
    }
}