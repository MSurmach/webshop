package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.orderorchestrator.producer.AbstractOrderOrchestratorCommandProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderOrchestratorCommandToOrderProducer extends AbstractOrderOrchestratorCommandProducer {
    public OrderOrchestratorCommandToOrderProducer(RabbitTemplate rabbitTemplate,
                                                   @Value("${rmq.command.order.exchange}") String exchangeToProduct,
                                                   @Value("${rmq.command.order.routing-prefix}") String routingPrefixToProduct) {
        super(rabbitTemplate);
        exchange = exchangeToProduct;
        routingPrefix = routingPrefixToProduct;
    }
}