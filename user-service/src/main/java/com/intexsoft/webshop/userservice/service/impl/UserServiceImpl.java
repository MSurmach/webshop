package com.intexsoft.webshop.userservice.service.impl;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.exception.SuchUserExistsException;
import com.intexsoft.webshop.userservice.mapper.UserMapper;
import com.intexsoft.webshop.userservice.model.User;
import com.intexsoft.webshop.userservice.repository.UserRepository;
import com.intexsoft.webshop.userservice.service.UserEventProducer;
import com.intexsoft.webshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEventProducer userEventProducer;

    @Override
    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        String userLogin = userCreateDto.getLogin();
        String userEmail = userCreateDto.getEmail();
        List<UserDto> foundedUsers = findUserByLoginOrEmail(userLogin, userEmail);
        if (!foundedUsers.isEmpty()) {
            StringBuilder exceptionMessageBuilder = new StringBuilder("Unable to save a new user:");
            foundedUsers.forEach(existedUser -> {
                if (Objects.equals(existedUser.getLogin().toLowerCase(), userLogin.toLowerCase()))
                    exceptionMessageBuilder.append(" such user login exists;");
                if (Objects.equals(existedUser.getEmail().toLowerCase(), userEmail.toLowerCase()))
                    exceptionMessageBuilder.append(" such user email exists;");
            });
            throw new SuchUserExistsException(exceptionMessageBuilder.toString());
        }
        User savedUser = userRepository.save(userMapper.toUser(userCreateDto));
        userEventProducer.produceUserCreatedEvent(userMapper.toUserCreatedEvent(savedUser));
        return userMapper.toUserDto(savedUser);
    }

    private List<UserDto> findUserByLoginOrEmail(String login, String email) {
        List<UserDto> foundedUsers = userRepository.
                findUserByLoginIgnoreCaseOrEmailIgnoreCase(login, email)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
        return foundedUsers;
    }
}