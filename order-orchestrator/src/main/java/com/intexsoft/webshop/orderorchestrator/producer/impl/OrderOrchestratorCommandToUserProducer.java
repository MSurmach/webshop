package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.orderorchestrator.producer.AbstractOrderOrchestratorCommandProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderOrchestratorCommandToUserProducer extends AbstractOrderOrchestratorCommandProducer {
    public OrderOrchestratorCommandToUserProducer(RabbitTemplate rabbitTemplate,
                                                  @Value("${rmq.command.user.exchange}") String exchangeToUser,
                                                  @Value("${rmq.command.user.routing-prefix}") String routingPrefixToUser) {
        super(rabbitTemplate);
        exchange = exchangeToUser;
        routingPrefix = routingPrefixToUser;
    }
}