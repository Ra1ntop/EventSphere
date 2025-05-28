package com.ra1n.top.userservice.service;

import com.ra1n.top.userservice.model.exception.Ra1nException;
import com.ra1n.top.userservice.persis.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Travkin Andrii
 * @version 28.05.2025
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new Ra1nException(
                        "User not found with email: " + username, HttpStatus.NOT_FOUND));
    }
}