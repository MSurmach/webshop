package com.intexsoft.webshop.orderorchestrator.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.shop.impl.OrderRequestToShopAddedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ProductCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.orderorchestrator.consumer.ShopEventConsumer;
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
        value = @Queue(name = "${rmq.event.shop.queue}"),
        exchange = @Exchange(name = "${rmq.event.shop.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.shop.routing}")
)
@Slf4j
public class ShopEventConsumerImpl implements ShopEventConsumer {
    private final OrderOrchestratorUserTaskProcessor orderOrchestratorUserTaskProcessor;

    @RabbitHandler
    @Override
    public void receiveShopCheckedEvent(@Payload ShopCheckedEvent shopCheckedEvent) {
        log.info("IN: New message {} received, payload = {}",
                shopCheckedEvent.getClass().getSimpleName(), JsonUtils.getAsString(shopCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckShopResult(shopCheckedEvent);
    }

    @RabbitHandler
    @Override
    public void receivePickupPointCheckedEvent(@Payload PickupPointCheckedEvent pickupPointCheckedEvent) {
        log.info("IN: new message {} received, payload = {}",
                pickupPointCheckedEvent.getClass().getSimpleName(), JsonUtils.getAsString(pickupPointCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckPickupPointResult(pickupPointCheckedEvent);
    }

    @RabbitHandler
    @Override
    public void receiveProductCheckedEvent(@Payload ProductCheckedEvent productCheckedEvent) {
        log.info("IN: new message {} received, payload = {}",
                productCheckedEvent.getClass().getSimpleName(), JsonUtils.getAsString(productCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckProductResult(productCheckedEvent);
    }
    @RabbitHandler
    @Override
    public void receiveOrderRequestToShopAddedEvent(@Payload OrderRequestToShopAddedEvent orderRequestToShopAddedEvent) {
        log.info("IN: new message {} received, payload = {}",
                orderRequestToShopAddedEvent.getClass().getSimpleName(), JsonUtils.getAsString(orderRequestToShopAddedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteOrderRequestResult(orderRequestToShopAddedEvent);
    }
}