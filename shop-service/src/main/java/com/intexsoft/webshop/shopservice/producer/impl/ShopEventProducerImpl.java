package com.intexsoft.webshop.shopservice.producer.impl;

import com.intexsoft.webshop.messagecommon.event.shop.CheckShopResultEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCreatedEvent;
import com.intexsoft.webshop.shopservice.producer.ShopEventProducer;
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
    @Value("${rmq.event.shop.routing-keys.shop_checked}")
    private String shopCheckedRoutingKey;

    @Value("${rmq.event.shop.routing-keys.pickup_point_checked}")
    private String pickupPickupPointCheckedRoutingKey;

    public void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                shopCreatedEvent.getClass().getName(), getAsString(shopCreatedEvent));
        String routing = routingPrefix + shopCreatedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, shopCreatedEvent);
    }

    @Override
    public void produceShopCheckedEvent(ShopCheckedEvent shopCheckedEvent) {
        String routing = routingPrefix + shopCheckedRoutingKey;
        produceCheckShopResultEvent(shopCheckedEvent, routing);
    }

    @Override
    public void producePickupPointCheckedEvent(PickupPointCheckedEvent pickupPointCheckedEvent) {
        String routing = routingPrefix + pickupPickupPointCheckedRoutingKey;
        produceCheckShopResultEvent(pickupPointCheckedEvent, routing);
    }

    private void produceCheckShopResultEvent(CheckShopResultEvent checkShopResultEvent, String routing) {
        log.info("IN: produce {} message. The event message = {}",
                checkShopResultEvent.getClass().getName(), getAsString(checkShopResultEvent));
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, checkShopResultEvent);
    }
}