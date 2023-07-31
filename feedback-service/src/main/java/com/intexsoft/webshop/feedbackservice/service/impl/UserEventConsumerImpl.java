package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.service.UserEventConsumer;
import com.intexsoft.webshop.feedbackservice.service.UsersReplicaService;
import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.feedbackservice.util.JsonUtils.getAsString;
import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.user.queue}"),
        exchange = @Exchange(name = "${rmq.event.user.exchange}", type = TOPIC),
        key = "${rmq.event.user.routing}")
)
@Slf4j
public class UserEventConsumerImpl implements UserEventConsumer {
    private final UsersReplicaService usersReplicaService;

    @RabbitHandler
    @Override
    public void receiveUserCreatedEvent(@Payload UserCreatedEvent userCreatedEvent) {
        log.info("New event message {} received. Message payload = {}",
                userCreatedEvent.getClass().getName(), getAsString(userCreatedEvent));
        usersReplicaService.createUsersReplica(userCreatedEvent);
    }
}