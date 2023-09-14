package com.intexsoft.webshop.productservicekt.dto;

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.time.LocalDateTime


data class ApiExceptionDto(
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    val exceptionTimestamp: LocalDateTime,
    @JsonProperty("message")
    val exceptionMessage: String,
    val status: HttpStatus,
    @JsonProperty("code")
    val statusCode: Int
)