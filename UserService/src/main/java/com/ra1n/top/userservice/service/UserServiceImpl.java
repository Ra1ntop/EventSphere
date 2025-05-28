package com.ra1n.top.userservice.service;

import com.ra1n.top.userservice.config.JwtUtils;
import com.ra1n.top.userservice.model.AbstractUser;
import com.ra1n.top.userservice.model.RoleType;
import com.ra1n.top.userservice.model.User;
import com.ra1n.top.userservice.model.dto.AuthResponse;
import com.ra1n.top.userservice.model.dto.LoginRequest;
import com.ra1n.top.userservice.model.dto.RegisterRequest;
import com.ra1n.top.userservice.model.exception.Ra1nException;
import com.ra1n.top.userservice.persis.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Travkin Andrii
 * @version 28.05.2025
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public String getUserId(String email) {
        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new Ra1nException("User with sub " + email + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new Ra1nException("Email already in use: " + request.email(), HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(RoleType.CLIENT);
        userRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        AbstractUser user = (AbstractUser) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user);
        return new AuthResponse(token);
    }
}
