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
        value = @Queue(name = "${rmq.user.queue}"),
        exchange = @Exchange(name = "${rmq.user.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.user.routing}")
)
public class UserConsumerImpl implements EventConsumer {
    private final EventService eventService;

    @RabbitHandler
    @Override
    public void receiveEvent(@Payload BaseEvent baseEvent) {
        log.info("IN: New event {} received", baseEvent.getClass().getSimpleName());
        eventService.saveEvent(baseEvent);
    }
}
