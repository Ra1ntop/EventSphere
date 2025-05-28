package com.ra1n.top.userservice.controller;

import com.ra1n.top.userservice.model.exception.Ra1nException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author Travkin Andrii
 * @version 28.05.2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Ra1nException.class)
    public ResponseEntity<Map<String, Object>> handleRa1nException(Ra1nException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(Map.of(
                        "status", ex.getStatus().value(),
                        "message", ex.getMessage()
                ));
    }
}
