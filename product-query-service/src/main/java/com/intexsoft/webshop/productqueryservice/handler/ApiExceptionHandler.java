package com.intexsoft.webshop.productqueryservice.handler;

import com.intexsoft.webshop.productqueryservice.dto.ApiExceptionDto;
import com.intexsoft.webshop.productqueryservice.exception.HttpStatusException;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<ApiExceptionDto> handleSuchUserExistsException(HttpServletRequest request,
                                                                         HttpStatusException exception) {
        HttpStatus exceptionStatus = exception.getStatus();
        ApiExceptionDto exceptionDto = ApiExceptionDto.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionTimestamp(exception.getTimeStamp())
                .status(exceptionStatus)
                .statusCode(exceptionStatus.value())
                .build();
        log.error("Server error response = {}. Request url = {}", JsonUtils.getAsString(exceptionDto), request.getRequestURL());
        return ResponseEntity.status(exceptionStatus).body(exceptionDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiExceptionDto> handleConstraintViolationException(HttpServletRequest request,
                                                                              ConstraintViolationException exception) {
        ApiExceptionDto exceptionDto = ApiExceptionDto.builder()
                .exceptionMessage(exception.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("; ")))
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        log.error("Constraints violation. Request url = {}, response body = {}",
                request.getRequestURL(), JsonUtils.getAsString(exceptionDto));
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionDto> handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                                 MethodArgumentNotValidException exception) {
        ApiExceptionDto exceptionDto = ApiExceptionDto.builder()
                .exceptionMessage(exception.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")))
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        log.error("Constraints violation. Request url = {}, response body = {}",
                request.getRequestURL(), JsonUtils.getAsString(exceptionDto));
        return ResponseEntity.badRequest().body(exceptionDto);
    }

}