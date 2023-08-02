package com.intexsoft.webshop.productservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import com.intexsoft.webshop.productservice.consumer.ShopEventConsumer;
import com.intexsoft.webshop.productservice.service.ShopReplicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;
import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.shop.queue}"),
        exchange = @Exchange(name = "${rmq.event.shop.exchange}", type = TOPIC),
        key = "${rmq.event.shop.routing}")
)
@Slf4j
public class ShopEventConsumerImpl implements ShopEventConsumer {
    private final ShopReplicaService shopReplicaService;

    @RabbitHandler
    @Override
    public void receiveShopCreatedEvent(@Payload ShopCreatedEvent shopCreatedEvent) {
        log.info("New event message {} received. Message payload = {}",
                shopCreatedEvent.getClass().getName(), getAsString(shopCreatedEvent));
        shopReplicaService.createShopReplica(shopCreatedEvent);
    }
}