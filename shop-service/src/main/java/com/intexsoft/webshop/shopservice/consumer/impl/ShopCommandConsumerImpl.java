package com.intexsoft.webshop.shopservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.shopservice.consumer.ShopCommandConsumer;
import com.intexsoft.webshop.shopservice.service.ShopCheckerService;
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
    private final ShopCheckerService shopCheckerService;

    @RabbitHandler
    @Override
    public void receiveCheckOrderShopCommand(@Payload CheckOrderShopCommand checkOrderShopCommand) {
        log.info("IN: new message {} received, payload = {}",
                checkOrderShopCommand.getClass().getName(), JsonUtils.getAsString(checkOrderShopCommand));
        shopCheckerService.checkShop(checkOrderShopCommand);
    }

    @RabbitHandler
    @Override
    public void receiveCheckOrderPickupPointCommand(@Payload CheckOrderPickupPointCommand checkOrderPickupPointCommand) {
        log.info("IN: new message {} received, payload = {}",
                checkOrderPickupPointCommand.getClass().getName(), JsonUtils.getAsString(checkOrderPickupPointCommand));
        shopCheckerService.checkPickupPoint(checkOrderPickupPointCommand);
    }
}