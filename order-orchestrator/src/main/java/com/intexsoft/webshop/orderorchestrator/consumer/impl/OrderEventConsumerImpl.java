package com.intexsoft.webshop.orderorchestrator.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderorchestrator.consumer.OrderEventConsumer;
import com.intexsoft.webshop.orderorchestrator.service.OrderProcessLauncher;
import com.intexsoft.webshop.orderorchestrator.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.order.queue}"),
        exchange = @Exchange(name = "${rmq.event.order.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.order.routing}")
)
@Slf4j
public class OrderEventConsumerImpl implements OrderEventConsumer {
    private final OrderProcessLauncher orderProcessLauncher;

    @RabbitHandler
    @Override
    public void receiveOrderInitializedEvent(@Payload OrderInitializedEvent orderInitializedEvent) {
        log.info("New event message {} received. Message payload = {}",
                orderInitializedEvent.getClass().getSimpleName(), JsonUtils.getAsString(orderInitializedEvent));
        orderProcessLauncher.startOrderProcess(orderInitializedEvent);
    }
}