package com.intexsoft.webshop.userservice.service;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserCreateDto userCreateDto);
}