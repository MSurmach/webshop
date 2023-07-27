package com.intexsoft.webshop.userservice.exception.handler;

import com.intexsoft.webshop.userservice.dto.UserApiExceptionDto;
import com.intexsoft.webshop.userservice.exception.SuchUserExistsException;
import com.intexsoft.webshop.userservice.mapper.UserApiMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class UserExceptionHandler {
    private final UserApiMapper userApiMapper;

    @ExceptionHandler(SuchUserExistsException.class)
    public ResponseEntity<UserApiExceptionDto> handleSuchUserExistsException(HttpServletRequest request,
                                                                             SuchUserExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userApiMapper.toUserApiExceptionDto(exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserApiExceptionDto> handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                                     MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(userApiMapper.toUserApiExceptionDto(exception));
    }
}