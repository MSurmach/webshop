package com.intexsoft.webshop.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class UserCreateDto {
    @NotBlank(message = "{user.login.notBlank}")
    @Size(max = 30, message = "{user.login.size}")
    String login;
    @NotBlank(message = "{user.firstname.notBlank}")
    @Size(max = 100, message = "{user.firstname.size}")
    String firstname;
    @NotBlank(message = "{user.lastname.notBlank}")
    @Size(max = 100, message = "{user.lastname.size}")
    String lastname;
    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 6, max = 16, message = "{user.password.size}")
    @JsonProperty("password")
    String plainPassword;
    @Email
    @Size(max = 100, message = "{user.email.size}")
    String email;
    @Nullable
    @Pattern(regexp = "[+]?\\d+", message = "{user.phoneNumber.pattern}")
    @Size(max = 50, message = "{user.phoneNumber.size}")
    String phoneNumber;
}
