package com.intexsoft.webshop.userservice.service.impl;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.exception.SuchUserExistsException;
import com.intexsoft.webshop.userservice.exception.UserNotFoundException;
import com.intexsoft.webshop.userservice.mapper.UserMapper;
import com.intexsoft.webshop.userservice.model.UserEntity;
import com.intexsoft.webshop.userservice.producer.UserEventProducer;
import com.intexsoft.webshop.userservice.repository.UserRepository;
import com.intexsoft.webshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.intexsoft.webshop.userservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserEventProducer userEventProducer;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        log.info("IN: trying to save a new user. The user details = {}", getAsString(userCreateDto));
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
        UserEntity newUserEntity = userMapper.toUser(userCreateDto);
        newUserEntity.setEncodedPassword(passwordEncoder.encode(userCreateDto.getPlainPassword()));
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        log.info("OUT: new user saved successfully. Saved user details = {}", getAsString(savedUserEntity));
        userEventProducer.produceUserCreatedEvent(userMapper.toUserCreatedEvent(savedUserEntity));
        return userMapper.toUserDto(savedUserEntity);
    }

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        log.info("IN: trying to find all users");
        List<UserDto> userDtos = userRepository.findAll(pageable).getContent().stream().map(userMapper::toUserDto).toList();
        log.info("Found {} users", userDtos.size());
        return userDtos;
    }

    @Override
    public UserDto findById(Long userId) {
        log.info("IN: trying to find user by id = {}", userId);
        UserEntity foundUserEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + "not found"));
        UserDto userDto = userMapper.toUserDto(foundUserEntity);
        log.info("OUT: user with id = {} found successfully", userId);
        return userDto;
    }

    @Override
    public void deleteById(Long userId) {
        log.info("IN: trying to delete user by id = {}", userId);
        userRepository.deleteById(userId);
        log.info("OUT: user with id = {} is deleted successfully", userId);
    }

    public List<UserDto> findUserByLoginOrEmail(String login, String email) {
        log.info("IN: trying to find user by login = {}, and email = {}",
                login, email);
        List<UserDto> foundedUsers = userRepository.
                findUserByLoginIgnoreCaseOrEmailIgnoreCase(login, email)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
        log.info("OUT: found {} users with login = {}, and email = {}",
                foundedUsers.size(), login, email);
        return foundedUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("IN: trying to find user by login = {}", username);
        UserEntity foundUserEntity = userRepository.findUserByLoginIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with login = " + username + " not found"));
        log.info("IN: the user with login = {} found successfully", username);
        return new User(foundUserEntity.getLogin(), foundUserEntity.getEncodedPassword(), Collections.emptyList());
    }
}