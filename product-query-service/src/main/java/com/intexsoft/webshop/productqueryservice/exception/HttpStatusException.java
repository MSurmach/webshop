package com.intexsoft.webshop.productqueryservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class HttpStatusException extends RuntimeException {
    private HttpStatus status;
    private LocalDateTime timeStamp;

    public HttpStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        timeStamp = LocalDateTime.now();
    }
}