package com.ra1n.top.userservice.controller;

import com.ra1n.top.userservice.config.JwtUtils;
import com.ra1n.top.userservice.model.RoleType;
import com.ra1n.top.userservice.model.User;
import com.ra1n.top.userservice.model.dto.AuthResponse;
import com.ra1n.top.userservice.model.dto.LoginRequest;
import com.ra1n.top.userservice.model.dto.RegisterRequest;
import com.ra1n.top.userservice.persis.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Travkin Andrii
 * @version 25.05.2025
 */
@RestController
@RequestMapping("/api-v1/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @GetMapping("/user-id")
    public ResponseEntity<String> getUserId(@RequestParam String sub) {
        Optional<User> user = userRepository.findByEmail(sub);
        return user.map(u -> ResponseEntity.ok(u.getId()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(RoleType.USER);

        userRepository.save(user);

        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
