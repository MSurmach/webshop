package com.intexsoft.webshop.userservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.userservice.consumer.UserCommandConsumer;
import com.intexsoft.webshop.userservice.service.UserCheckerService;
import com.intexsoft.webshop.userservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.command.queue}"),
        exchange = @Exchange(name = "${rmq.command.exchange}", type = TOPIC),
        key = "${rmq.command.routing}")
)
@Slf4j
public class UserCommandConsumerImpl implements UserCommandConsumer {
    private final UserCheckerService userCheckerService;

    @RabbitHandler
    @Override
    public void receiveCheckOrderUserCommand(@Payload CheckOrderUserCommand checkOrderUserCommand) {
        log.info("New event message {} received. Message payload = {}",
                checkOrderUserCommand.getClass().getName(), JsonUtils.getAsString(checkOrderUserCommand));
        userCheckerService.checkUser(checkOrderUserCommand);
    }
}
