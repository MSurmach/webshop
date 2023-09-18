package com.intexsoft.webshop.productservicekt.handler

import com.intexsoft.webshop.productservicekt.dto.ApiExceptionDto
import com.intexsoft.webshop.productservicekt.exception.HttpStatusException
import com.intexsoft.webshop.productservicekt.log
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(HttpStatusException::class)
    fun handleSuchUserExistsException(
        request: HttpServletRequest,
        exception: HttpStatusException
    ): ResponseEntity<ApiExceptionDto> {
        val exceptionStatus: HttpStatus = exception.status
        val exceptionDto = ApiExceptionDto(
            exceptionMessage = exception.exceptionMessage,
            status = exception.status,
            statusCode = exception.status.value(),
            exceptionTimestamp = exception.timeStamp
        )
        log.error("Server error response = $exceptionDto. Request url = ${request.requestURL}")
        return ResponseEntity.status(exceptionStatus).body(exceptionDto)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        request: HttpServletRequest,
        exception: ConstraintViolationException
    ): ResponseEntity<ApiExceptionDto> {
        val exceptionDto = ApiExceptionDto(
            exceptionMessage = exception.constraintViolations
                .joinToString(separator = "; ") { "${it.propertyPath.last().name} : ${it.message}" },
            exceptionTimestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST,
            statusCode = HttpStatus.BAD_REQUEST.value()
        )
        log.error("Constraints violation. Request url = ${request.requestURL}, response body = $exceptionDto")
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        request: HttpServletRequest,
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiExceptionDto> {
        print("something")
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
}