package com.intexsoft.webshop.eventbus.consumer.impl;

import com.intexsoft.webshop.eventbus.consumer.EventConsumer;
import com.intexsoft.webshop.eventbus.service.EventService;
import com.intexsoft.webshop.messagecommon.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.product.queue}"),
        exchange = @Exchange(name = "${rmq.product.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.product.routing}")
)
public class ProductEventConsumerImpl implements EventConsumer {
    private final EventService eventService;

    @RabbitHandler
    @Override
    public void receiveEvent(@Payload BaseEvent baseEvent) {
        log.info("IN: New event {} received", baseEvent.getClass().getSimpleName());
        eventService.saveEvent(baseEvent);
    }
}
