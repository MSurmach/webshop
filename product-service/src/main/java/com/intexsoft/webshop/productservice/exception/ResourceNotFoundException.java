package com.intexsoft.webshop.productservice.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HttpStatusException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}