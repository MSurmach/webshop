package com.intexsoft.webshop.shopservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.shopservice.consumer.ProductEventConsumer;
import com.intexsoft.webshop.shopservice.service.ProductReplicaService;
import com.intexsoft.webshop.shopservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.product.queue}"),
        exchange = @Exchange(name = "${rmq.event.product.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.product.routing}")
)
@Slf4j
public class ProductEventConsumerImpl implements ProductEventConsumer {
    private final ProductReplicaService productReplicaService;

    @Override
    @RabbitHandler
    public void receiveProductCreatedEvent(@Payload ProductCreatedEvent productCreatedEvent) {
        log.info("IN: New message {} received, payload = {}",
                productCreatedEvent.getClass().getSimpleName(), JsonUtils.getAsString(productCreatedEvent));
        productReplicaService.saveProductFromProductCreatedEvent(productCreatedEvent);
    }
}