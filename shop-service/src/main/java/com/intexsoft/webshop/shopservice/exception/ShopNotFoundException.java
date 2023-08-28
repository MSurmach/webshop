package com.intexsoft.webshop.shopservice.exception;

import org.springframework.http.HttpStatus;

public class ShopNotFoundException extends HttpStatusException {
    public ShopNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}