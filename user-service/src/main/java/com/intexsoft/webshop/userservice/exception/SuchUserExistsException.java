package com.intexsoft.webshop.userservice.exception;

public class SuchUserExistsException extends RuntimeException {
    public SuchUserExistsException(String message) {
        super(message);
    }
}