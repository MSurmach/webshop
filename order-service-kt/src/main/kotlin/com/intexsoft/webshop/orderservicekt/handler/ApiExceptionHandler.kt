package com.intexsoft.webshop.orderservicekt.handler

import com.fasterxml.jackson.databind.JsonMappingException
import com.intexsoft.webshop.orderservicekt.dto.ApiExceptionDto
import com.intexsoft.webshop.orderservicekt.log
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        request: HttpServletRequest,
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiExceptionDto> {
        val exceptionDto = ApiExceptionDto(
            exceptionMessage = exception.bindingResult.allErrors
                .joinToString(separator = "; ") {
                    "${it.codes?.first()?.split(".")?.last()} : ${it.defaultMessage!!}"
                },
            status = HttpStatus.BAD_REQUEST,
            statusCode = HttpStatus.BAD_REQUEST.value(),
            exceptionTimestamp = LocalDateTime.now()
        )
        log.error("Constraints violation. Request url = ${request.requestURL}, response body = $exceptionDto")
        return ResponseEntity.badRequest().body(exceptionDto)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        request: HttpServletRequest,
        exception: JsonMappingException
    ): ResponseEntity<ApiExceptionDto> {
        val exceptionDto = ApiExceptionDto(
            exceptionMessage = exception.originalMessage,
            status = HttpStatus.BAD_REQUEST,
            statusCode = HttpStatus.BAD_REQUEST.value(),
            exceptionTimestamp = LocalDateTime.now()
        )
        log.error("Violation in the request. Request url = ${request.requestURL}, response body = $exceptionDto")
        return ResponseEntity.badRequest().body(exceptionDto)
    }
}