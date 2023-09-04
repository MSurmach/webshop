package com.intexsoft.webshop.productqueryservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.consumer.ProductEventConsumer;
import com.intexsoft.webshop.productqueryservice.service.ProductQueryService;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
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
    private final ProductQueryService productQueryService;
    @Override
    @RabbitHandler
    public void receiveProductCreatedEvent(@Payload ProductCreatedEvent productCreatedEvent) {
        log.info("IN: New message {} received, payload = {}",
                productCreatedEvent.getClass().getSimpleName(), JsonUtils.getAsString(productCreatedEvent));
        productQueryService.saveProductFromProductCreatedEvent(productCreatedEvent);
    }
}
