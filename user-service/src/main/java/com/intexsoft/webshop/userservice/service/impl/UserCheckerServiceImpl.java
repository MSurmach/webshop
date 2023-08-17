package com.intexsoft.webshop.userservice.service.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.userservice.mapper.UserEventMapper;
import com.intexsoft.webshop.userservice.producer.UserEventProducer;
import com.intexsoft.webshop.userservice.repository.UserRepository;
import com.intexsoft.webshop.userservice.service.UserCheckerService;
import com.intexsoft.webshop.userservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCheckerServiceImpl implements UserCheckerService {
    private final UserRepository userRepository;
    private final UserEventProducer userEventProducer;
    private final UserEventMapper userEventMapper;

    @Override
    public void checkUser(CheckOrderUserCommand checkOrderUserCommand) {
        Long userId = checkOrderUserCommand.getUserId();
        log.info("IN: try to check user with id = {}. Message payload = {}",
                userId, JsonUtils.getAsString(checkOrderUserCommand));
        boolean isUserExists = userRepository.existsById(userId);
        log.info("OUT: check user with id = {} result is {}",
                userId, isUserExists);
        userEventProducer.produceUserCheckedEvent(userEventMapper.toUserCheckedEvent(isUserExists,
                checkOrderUserCommand));
    }
}
