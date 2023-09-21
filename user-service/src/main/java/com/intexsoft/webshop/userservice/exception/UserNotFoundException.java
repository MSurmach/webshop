package com.intexsoft.webshop.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpStatusException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}