package com.intexsoft.webshop.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty("id")
    Long id;
    @JsonProperty("login")
    String login;
    @JsonProperty("firstname")
    String firstname;
    @JsonProperty("lastname")
    String lastname;
    @JsonProperty("email")
    String email;
    @Nullable
    @JsonProperty("phoneNumber")
    String phoneNumber;
}