package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.service.ShopEventConsumer;
import com.intexsoft.webshop.productservice.service.ShopReplicaService;
import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.shop.queue}"),
        exchange = @Exchange(name = "${rmq.event.shop.exchange}", type = TOPIC),
        key = "${rmq.event.shop.routing}")
)
public class ShopEventConsumerImpl implements ShopEventConsumer {
    private final ShopReplicaService shopReplicaService;

    @RabbitHandler
    @Override
    public void receiveShopCreatedEvent(@Payload ShopCreatedEvent shopCreatedEvent) {
        shopReplicaService.createShopReplica(shopCreatedEvent);
    }
}
