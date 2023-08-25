package com.intexsoft.webshop.shopservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.OrderRequestToShopCommand;
import com.intexsoft.webshop.shopservice.consumer.ShopCommandConsumer;
import com.intexsoft.webshop.shopservice.service.ShopCommandProcessor;
import com.intexsoft.webshop.shopservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.command.queue}"),
        exchange = @Exchange(name = "${rmq.command.exchange}", type = TOPIC),
        key = "${rmq.command.routing}")
)
@Slf4j
public class ShopCommandConsumerImpl implements ShopCommandConsumer {
    private final ShopCommandProcessor shopCommandProcessor;

    @RabbitHandler
    @Override
    public void receiveCheckOrderShopCommand(@Payload CheckOrderShopCommand checkOrderShopCommand) {
        log.info("IN: new message {} received, payload = {}",
                checkOrderShopCommand.getClass().getSimpleName(), JsonUtils.getAsString(checkOrderShopCommand));
        shopCommandProcessor.checkShop(checkOrderShopCommand);
    }

    @RabbitHandler
    @Override
    public void receiveCheckOrderPickupPointCommand(@Payload CheckOrderPickupPointCommand checkOrderPickupPointCommand) {
        log.info("IN: new message {} received, payload = {}",
                checkOrderPickupPointCommand.getClass().getSimpleName(), JsonUtils.getAsString(checkOrderPickupPointCommand));
        shopCommandProcessor.checkPickupPoint(checkOrderPickupPointCommand);
    }

    @RabbitHandler
    @Override
    public void receiveCheckOrderProductCommand(@Payload CheckOrderProductCommand checkOrderProductCommand) {
        log.info("IN: new message {} received, payload = {}",
                checkOrderProductCommand.getClass().getSimpleName(), JsonUtils.getAsString(checkOrderProductCommand));
        shopCommandProcessor.checkProduct(checkOrderProductCommand);
    }

    @RabbitHandler
    @Override
    public void receiveOrderRequestToShopCommand(@Payload OrderRequestToShopCommand orderRequestToShopCommand) {
        log.info("IN: new message {} received, payload = {}",
                orderRequestToShopCommand.getClass().getSimpleName(), JsonUtils.getAsString(orderRequestToShopCommand));
        shopCommandProcessor.addOrderRequest(orderRequestToShopCommand);
    }
}