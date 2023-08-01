package com.intexsoft.webshop.userservice.controller;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.intexsoft.webshop.userservice.util.JsonUtils.getAsString;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        log.info("IN: request to create a new user received. Request body = {}",
                getAsString(userCreateDto));
        UserDto createdUserDto = userService.createUser(userCreateDto);
        log.info("OUT: new user created successfully. Response body = {}",
                getAsString(createdUserDto));
        return ResponseEntity.ok(createdUserDto);
    }
}