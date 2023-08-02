package com.intexsoft.webshop.productservice.exception;

import org.springframework.http.HttpStatus;

public class SuchResourceExistsException extends HttpStatusException {
    public SuchResourceExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}