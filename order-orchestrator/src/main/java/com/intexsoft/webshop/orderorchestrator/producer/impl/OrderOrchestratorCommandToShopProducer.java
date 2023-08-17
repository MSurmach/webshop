package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.orderorchestrator.producer.AbstractOrderOrchestratorCommandProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderOrchestratorCommandToShopProducer extends AbstractOrderOrchestratorCommandProducer {


    public OrderOrchestratorCommandToShopProducer(RabbitTemplate rabbitTemplate,
                                                  @Value("${rmq.command.shop.exchange}") String exchangeToShop,
                                                  @Value("${rmq.command.shop.routing-prefix}") String routingPrefixToShop) {
        super(rabbitTemplate);
        exchange = exchangeToShop;
        routingPrefix = routingPrefixToShop;
    }
}