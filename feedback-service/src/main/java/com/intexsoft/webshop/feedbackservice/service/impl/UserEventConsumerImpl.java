package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.mapper.FeedbackApiMapper;
import com.intexsoft.webshop.feedbackservice.service.UserEventConsumer;
import com.intexsoft.webshop.feedbackservice.service.UsersReplicaService;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.user.queue}"),
        exchange = @Exchange(name = "${rmq.event.user.exchange}", type = TOPIC),
        key = "${rmq.event.user.routing}")
)
public class UserEventConsumerImpl implements UserEventConsumer {
    private final UsersReplicaService usersReplicaService;
    private final FeedbackApiMapper feedbackApiMapper;

    @RabbitHandler
    @Override
    public void receive(@Payload UserCreatedEvent userCreatedEvent) {
        usersReplicaService.createUsersReplica(feedbackApiMapper.toUsersReplicaDto(userCreatedEvent));
    }
}
