package com.intexsoft.webshop.shopservice.exception;

public class SuchShopExistsException extends RuntimeException {
    public SuchShopExistsException(String message) {
        super(message);
    }
}