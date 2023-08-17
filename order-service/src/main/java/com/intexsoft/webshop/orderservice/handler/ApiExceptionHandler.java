package com.intexsoft.webshop.orderservice.handler;

import com.intexsoft.webshop.orderservice.dto.ApiExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
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

import static com.intexsoft.webshop.orderservice.util.JsonUtils.getAsString;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler {

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
                request.getRequestURL(), getAsString(exceptionDto));
        return ResponseEntity.badRequest().body(exceptionDto);
    }
}