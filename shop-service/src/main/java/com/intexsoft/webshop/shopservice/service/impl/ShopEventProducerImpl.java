package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import com.intexsoft.webshop.shopservice.service.ShopEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.shopservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopEventProducerImpl implements ShopEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.shop.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.shop.routing-keys.shop_created}")
    private String shopCreatedRoutingKey;

    public void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                shopCreatedEvent.getClass().getName(), getAsString(shopCreatedEvent));
        String routing = routingPrefix + shopCreatedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, shopCreatedEvent);
    }
}