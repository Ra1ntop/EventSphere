package com.ra1n.top.userservice.model.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Travkin Andrii
 * @version 28.05.2025
 */

public class Ra1nException extends RuntimeException {
    private final HttpStatus status;

    public Ra1nException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
