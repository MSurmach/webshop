package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.shopservice.service.ShopEventProducer;
import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopEventProducerImpl implements ShopEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.shop.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.shop.routing-keys.shop_created}")
    private String shopCreatedRoutingKey;

    public void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent) {
        rabbitTemplate.convertAndSend(eventExchange.getName(),
                routingPrefix + shopCreatedRoutingKey,
                shopCreatedEvent);
    }
}