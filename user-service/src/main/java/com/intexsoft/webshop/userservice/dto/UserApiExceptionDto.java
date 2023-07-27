package com.intexsoft.webshop.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserApiExceptionDto {
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime exceptionTimestamp;
    @JsonProperty("message")
    String exceptionMessage;
    @JsonProperty("status")
    HttpStatus status;
    @JsonProperty("code")
    int statusCode;
}