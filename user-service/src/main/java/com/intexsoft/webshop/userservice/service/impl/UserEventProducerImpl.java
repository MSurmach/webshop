package com.intexsoft.webshop.userservice.service.impl;

import com.intexsoft.webshop.userservice.service.UserEventProducer;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducerImpl implements UserEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.user.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.user.routing-keys.user_created}")
    private String userCreatedRoutingKey;

    @Override
    public void produceUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        rabbitTemplate.convertAndSend(eventExchange.getName(),
                routingPrefix + userCreatedRoutingKey,
                userCreatedEvent);
    }
}
