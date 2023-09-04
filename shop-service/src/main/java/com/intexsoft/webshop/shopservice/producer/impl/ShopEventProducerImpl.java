package com.intexsoft.webshop.shopservice.producer.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.*;
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
    @Value("${rmq.event.shop.routing-keys.shop_product_link_created}")
    private String shopProductLinkCreatedRoutingKey;
    @Value("${rmq.event.shop.routing-keys.shop_checked}")
    private String shopCheckedRoutingKey;

    @Value("${rmq.event.shop.routing-keys.pickup_point_checked}")
    private String pickupPickupPointCheckedRoutingKey;
    @Value("${rmq.event.shop.routing-keys.order_product_checked}")
    private String orderProductCheckedRoutingKey;
    @Value("${rmq.event.shop.routing-keys.order_request_added}")
    private String orderRequestToShopAddedRoutingKey;

    public void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent) {
        String routing = routingPrefix + shopCreatedRoutingKey;
        produceShopEvent(shopCreatedEvent, routing);
    }

    @Override
    public void produceShopCheckedEvent(ShopCheckedEvent shopCheckedEvent) {
        String routing = routingPrefix + shopCheckedRoutingKey;
        produceShopEvent(shopCheckedEvent, routing);
    }

    @Override
    public void producePickupPointCheckedEvent(PickupPointCheckedEvent pickupPointCheckedEvent) {
        String routing = routingPrefix + pickupPickupPointCheckedRoutingKey;
        produceShopEvent(pickupPointCheckedEvent, routing);
    }

    @Override
    public void produceOrderProductCheckedEvent(ProductCheckedEvent productCheckedEvent) {
        String routing = routingPrefix + orderProductCheckedRoutingKey;
        produceShopEvent(productCheckedEvent, routing);
    }

    @Override
    public void produceOrderRequestToShopAddedEvent(OrderRequestToShopAddedEvent orderRequestToShopAddedEvent) {
        String routing = routingPrefix + orderRequestToShopAddedRoutingKey;
        produceShopEvent(orderRequestToShopAddedEvent, routing);
    }

    @Override
    public void produceShopProductLinkCreatedEvent(ShopProductLinkCreatedEvent shopProductLinkCreatedEvent) {
        String routing = routingPrefix + shopProductLinkCreatedRoutingKey;
        produceShopEvent(shopProductLinkCreatedEvent, routing);
    }

    private void produceShopEvent(ShopEvent shopEvent, String routing) {
        log.info("IN: try to produce {}, payload = {}",
                shopEvent.getClass().getSimpleName(), getAsString(shopEvent));
        log.info("IN: message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, shopEvent);
    }
}