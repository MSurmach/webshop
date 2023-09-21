package com.intexsoft.webshop.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginDto {
    @NotBlank(message = "{user.login.notBlank}")
    @Size(max = 30, message = "{user.login.size}")
    String login;
    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 6, max = 16, message = "{user.password.size}")
    @JsonProperty("password")
    String plainPassword;
}
