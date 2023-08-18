package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.orderorchestrator.producer.AbstractOrderOrchestratorCommandProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderOrchestratorCommandToProductProducer extends AbstractOrderOrchestratorCommandProducer {
    public OrderOrchestratorCommandToProductProducer(RabbitTemplate rabbitTemplate,
                                                     @Value("${rmq.command.product.exchange}") String exchangeToProduct,
                                                     @Value("${rmq.command.product.routing-prefix}") String routingPrefixToProduct) {
        super(rabbitTemplate);
        exchange = exchangeToProduct;
        routingPrefix = routingPrefixToProduct;
    }
}