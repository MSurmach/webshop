package com.intexsoft.webshop.orderorchestrator.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.orderorchestrator.consumer.ProductEventConsumer;
import com.intexsoft.webshop.orderorchestrator.service.OrderOrchestratorUserTaskProcessor;
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
        value = @Queue(name = "${rmq.event.product.queue}"),
        exchange = @Exchange(name = "${rmq.event.product.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.product.routing}")
)
@Slf4j
public class ProductEventConsumerImpl implements ProductEventConsumer {
    private final OrderOrchestratorUserTaskProcessor orderOrchestratorUserTaskProcessor;

    @RabbitHandler
    @Override
    public void receiveProductOrderQuantityIncrementedEvent(@Payload ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent) {
        log.info("IN: New message {} received, payload = {}",
                productOrderQuantityIncrementedEvent.getClass().getSimpleName(), JsonUtils.getAsString(productOrderQuantityIncrementedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteProductOrderQuantityIncrementedResult(productOrderQuantityIncrementedEvent);
    }
}