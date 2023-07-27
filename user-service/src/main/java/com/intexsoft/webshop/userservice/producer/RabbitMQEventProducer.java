package com.intexsoft.webshop.userservice.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.user.routing-key-prefix}")
    private String routingKeyPrefix;

    public void produceEvent(String routingKey, Object object) {
        rabbitTemplate.convertAndSend(eventExchange.getName(), routingKeyPrefix + routingKey, object);
    }
}