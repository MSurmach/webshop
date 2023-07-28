package com.intexsoft.webshop.userservice.handler;

import com.intexsoft.webshop.userservice.dto.UserApiExceptionDto;
import com.intexsoft.webshop.userservice.exception.HttpStatusException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class UserExceptionHandler {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<UserApiExceptionDto> handleSuchUserExistsException(HttpServletRequest request,
                                                                             HttpStatusException exception) {
        HttpStatus exceptionStatus = exception.getStatus();
        UserApiExceptionDto exceptionDto = UserApiExceptionDto.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionTimestamp(exception.getTimeStamp())
                .status(exceptionStatus)
                .statusCode(exceptionStatus.value())
                .build();
        return ResponseEntity.status(exceptionStatus).body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserApiExceptionDto> handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                                     MethodArgumentNotValidException exception) {
        UserApiExceptionDto exceptionDto = UserApiExceptionDto.builder()
                .exceptionMessage(exception.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")))
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.badRequest().body(exceptionDto);
    }
}