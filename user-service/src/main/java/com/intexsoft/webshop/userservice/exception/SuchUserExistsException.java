package com.intexsoft.webshop.userservice.exception;

import org.springframework.http.HttpStatus;

public class SuchUserExistsException extends HttpStatusException {
    public SuchUserExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}