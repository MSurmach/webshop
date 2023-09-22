package com.intexsoft.webshop.userservice.controller;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.service.UserService;
import com.intexsoft.webshop.userservice.util.JsonUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.intexsoft.webshop.userservice.util.JsonUtils.getAsString;
import static java.lang.Integer.MAX_VALUE;

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

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@PageableDefault(size = MAX_VALUE) Pageable pageable) {
        log.info("IN: request to find all users received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<UserDto> userDtos = userService.findAll(pageable);
        log.info("OUT: response will return {} users", userDtos.size());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable @Positive Long userId) {
        log.info("IN: request to find a user by id = {} received", userId);
        UserDto userDto = userService.findById(userId);
        log.info("OUT: the user with id = {} found successfully. Response body = {}",
                userId, JsonUtils.getAsString(userDto));
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteById(@PathVariable @Positive Long userId) {
        log.info("IN: request to delete a user by id = {} received", userId);
        userService.deleteById(userId);
        log.info("OUT: the user with id = {} deleted successfully", userId);
        return ResponseEntity.noContent().build();
    }
}