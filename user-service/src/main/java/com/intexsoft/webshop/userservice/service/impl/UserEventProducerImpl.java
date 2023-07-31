package com.intexsoft.webshop.userservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;
import com.intexsoft.webshop.userservice.service.UserEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.userservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventProducerImpl implements UserEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.user.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.user.routing-keys.user_created}")
    private String userCreatedRoutingKey;

    @Override
    public void produceUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                userCreatedEvent.getClass().getName(), getAsString(userCreatedEvent));
        String routing = routingPrefix + userCreatedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, userCreatedEvent);
    }
}