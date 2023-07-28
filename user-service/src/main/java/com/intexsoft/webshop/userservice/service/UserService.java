package com.intexsoft.webshop.userservice.service;

import com.intexsoft.webshop.userservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> findUserByLoginOrEmail(UserDto userDto);
}