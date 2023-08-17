package com.intexsoft.webshop.orderorchestrator.consumer.impl;

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
        log.info("New event message {} received. Message payload = {}",
                shopCheckedEvent.getClass().getName(), JsonUtils.getAsString(shopCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckShopResult(shopCheckedEvent);
    }

    @RabbitHandler
    @Override
    public void receivePickupPointCheckedEvent(@Payload PickupPointCheckedEvent pickupPointCheckedEvent) {
        log.info("New event message {} received. Message payload = {}",
                pickupPointCheckedEvent.getClass().getName(), JsonUtils.getAsString(pickupPointCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckPickupPointResult(pickupPointCheckedEvent);
    }
}