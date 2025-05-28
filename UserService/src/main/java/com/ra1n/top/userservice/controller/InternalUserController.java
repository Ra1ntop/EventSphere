package com.ra1n.top.userservice.controller;

import com.ra1n.top.userservice.model.dto.AuthResponse;
import com.ra1n.top.userservice.model.dto.LoginRequest;
import com.ra1n.top.userservice.model.dto.RegisterRequest;
import com.ra1n.top.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Travkin Andrii
 * @version 25.05.2025
 */
@RestController
@RequestMapping("/api-v1/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping("/user-id")
    public ResponseEntity<String> getUserId(@RequestParam String sub) {
        return ResponseEntity.ok(userService.getUserId(sub));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
