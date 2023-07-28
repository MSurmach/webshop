package com.intexsoft.webshop.shopservice.handler;

import com.intexsoft.webshop.shopservice.dto.ShopApiExceptionDto;
import com.intexsoft.webshop.shopservice.exception.SuchShopExistsException;
import com.intexsoft.webshop.shopservice.mapper.ShopApiMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ShopExceptionHandler {
    private final ShopApiMapper shopApiMapper;

    @ExceptionHandler(SuchShopExistsException.class)
    public ResponseEntity<ShopApiExceptionDto> handleSuchUserExistsException(HttpServletRequest request,
                                                                             SuchShopExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(shopApiMapper.toShopApiExceptionDto(exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ShopApiExceptionDto> handleMethodArgumentNotValidException(HttpServletRequest request,
                                                                                     MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(shopApiMapper.toShopApiExceptionDto(exception));
    }
}