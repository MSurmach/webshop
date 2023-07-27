package com.intexsoft.webshop.userservice.service.imp;

import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.exception.SuchUserExistsException;
import com.intexsoft.webshop.userservice.mapper.UserApiMapper;
import com.intexsoft.webshop.userservice.model.User;
import com.intexsoft.webshop.userservice.producer.RabbitMQEventProducer;
import com.intexsoft.webshop.userservice.repository.UserRepository;
import com.intexsoft.webshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final UserApiMapper userApiMapper;
    private final RabbitMQEventProducer rabbitMQEventProducer;
    @Value("${rmq.event.user.routing-keys.user_created}")
    private String createdUserRoutingKey;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        List<UserDto> foundedUsers = findUserByLoginOrEmail(userDto);
        if (!foundedUsers.isEmpty()) {
            StringBuilder exceptionMessageBuilder = new StringBuilder("Unable to save a new user:");
            foundedUsers.forEach(existedUser -> {
                if (Objects.equals(existedUser.getLogin().toLowerCase(), userDto.getLogin().toLowerCase()))
                    exceptionMessageBuilder.append(" such login exists;");
                if (Objects.equals(existedUser.getEmail().toLowerCase(), userDto.getEmail().toLowerCase()))
                    exceptionMessageBuilder.append(" such email exists;");
            });
            throw new SuchUserExistsException(exceptionMessageBuilder.toString());
        }
        User savedUser = userRepository.save(userApiMapper.toUser(userDto));
        rabbitMQEventProducer.produceEvent(createdUserRoutingKey, userApiMapper.toUserCreatedEvent(savedUser));
        return userApiMapper.toUserDto(savedUser);
    }

    public List<UserDto> findUserByLoginOrEmail(UserDto userDto) {
        List<UserDto> foundedUsers = userRepository.
                findUserByLoginIgnoreCaseOrEmailIgnoreCase(userDto.getLogin(), userDto.getEmail())
                .stream()
                .map(userApiMapper::toUserDto)
                .collect(Collectors.toList());
        return foundedUsers;
    }
}