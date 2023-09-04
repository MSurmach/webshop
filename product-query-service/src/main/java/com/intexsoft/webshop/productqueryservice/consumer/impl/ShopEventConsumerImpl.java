package com.intexsoft.webshop.productqueryservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopProductLinkCreatedEvent;
import com.intexsoft.webshop.productqueryservice.consumer.ShopEventConsumer;
import com.intexsoft.webshop.productqueryservice.service.ShopProductLinkService;
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
        value = @Queue(name = "${rmq.event.shop.queue}"),
        exchange = @Exchange(name = "${rmq.event.shop.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.shop.routing}")
)
@Slf4j
public class ShopEventConsumerImpl implements ShopEventConsumer {
    private final ShopProductLinkService shopProductLinkService;

    @Override
    @RabbitHandler
    public void receiveShopProductLinkCreatedEvent(@Payload ShopProductLinkCreatedEvent shopProductLinkCreatedEvent) {
        log.info("IN: New message {} received, payload = {}",
                shopProductLinkCreatedEvent.getClass().getSimpleName(), JsonUtils.getAsString(shopProductLinkCreatedEvent));
        shopProductLinkService.saveShopProductLinkFromShopProductLinkCreatedEvent(shopProductLinkCreatedEvent);
    }
}