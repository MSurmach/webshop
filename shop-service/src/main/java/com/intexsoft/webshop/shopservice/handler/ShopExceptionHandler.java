package com.intexsoft.webshop.shopservice.handler;

import com.intexsoft.webshop.shopservice.dto.ShopApiExceptionDto;
import com.intexsoft.webshop.shopservice.exception.HttpStatusException;
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

import static com.intexsoft.webshop.shopservice.util.JsonUtils.getAsString;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ShopExceptionHandler {

    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<ShopApiExceptionDto> handleSuchUserExistsException(HttpServletRequest request,
                                                                             HttpStatusException exception) {
        HttpStatus exceptionStatus = exception.getStatus();
        ShopApiExceptionDto exceptionDto = ShopApiExceptionDto.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionTimestamp(exception.getTimeStamp())
                .status(exceptionStatus)
                .statusCode(exceptionStatus.value())
                .build();
        log.error("Server error response = {}. Request url = {}", getAsString(exceptionDto), request.getRequestURL());
        return ResponseEntity.status(exceptionStatus).body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ShopApiExceptionDto> handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                                     MethodArgumentNotValidException exception) {
        ShopApiExceptionDto exceptionDto = ShopApiExceptionDto.builder()
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