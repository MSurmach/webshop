package com.intexsoft.webshop.shopservice.exception;

import org.springframework.http.HttpStatus;

public class ProductReplicaNotFoundException extends HttpStatusException {
    public ProductReplicaNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}