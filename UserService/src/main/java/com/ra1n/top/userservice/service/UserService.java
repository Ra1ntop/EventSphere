package com.ra1n.top.userservice.service;

import com.ra1n.top.userservice.model.dto.AuthResponse;
import com.ra1n.top.userservice.model.dto.LoginRequest;
import com.ra1n.top.userservice.model.dto.RegisterRequest;

/**
 * @author Travkin Andrii
 * @version 28.05.2025
 */
public interface UserService {

    String getUserId(String email);

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
