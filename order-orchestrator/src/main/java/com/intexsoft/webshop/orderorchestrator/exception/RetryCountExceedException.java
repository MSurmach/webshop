package com.intexsoft.webshop.orderorchestrator.exception;

public class RetryCountExceedException extends RuntimeException {
    private final static String DEFAULT_MSG = "The max number of retries was exceeded";

    public RetryCountExceedException() {
        super(DEFAULT_MSG);
    }

    public RetryCountExceedException(String message) {
        super(message);
    }
}
