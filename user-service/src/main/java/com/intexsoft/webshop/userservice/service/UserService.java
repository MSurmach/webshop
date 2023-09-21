package com.intexsoft.webshop.userservice.service;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserCreateDto userCreateDto);

    List<UserDto> findAll(Pageable pageable);

    UserDto findById(Long userId);

    void deleteById(Long userId);
}