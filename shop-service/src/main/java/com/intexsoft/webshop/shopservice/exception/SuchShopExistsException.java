package com.intexsoft.webshop.shopservice.exception;

import org.springframework.http.HttpStatus;

public class SuchShopExistsException extends HttpStatusException {
    public SuchShopExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}