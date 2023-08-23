package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;
import com.intexsoft.webshop.orderorchestrator.producer.OrderOrchestratorCommandProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderOrchestratorCommandProducerImpl implements OrderOrchestratorCommandProducer {
    private final RabbitTemplate rabbitTemplate;

    // to order-service
    @Value("${rmq.command.order.exchange}")
    private String exchangeToOrder;
    @Value("${rmq.command.order.routing-prefix}")
    private String routingPrefixToOrder;
    @Value("${rmq.command.order.routing-keys.fail_order}")
    private String failOrderRoutingKey;
    // to shop-service
    @Value("${rmq.command.shop.exchange}")
    private String exchangeToShop;
    @Value("${rmq.command.shop.routing-prefix}")
    private String routingPrefixToShop;
    @Value("${rmq.command.shop.routing-keys.check_pickup_point}")
    private String checkPickupPointPrefixKey;
    @Value("${rmq.command.shop.routing-keys.check_shop}")
    private String checkShopPrefixKey;
    //to user-service
    @Value("${rmq.command.user.exchange}")
    private String exchangeToUser;
    @Value("${rmq.command.user.routing-prefix}")
    private String routingPrefixToUser;
    @Value("${rmq.command.user.routing-keys.check_user}")
    private String checkUserPrefixKey;

    @Override
    public void produceCheckOrderPickupPointCommand(CheckOrderPickupPointCommand checkOrderPickupPointCommand) {
        log.info("IN: try to produce a message = {}", checkOrderPickupPointCommand.getClass().getSimpleName());
        String routing = routingPrefixToShop + checkPickupPointPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToShop, routing);
        rabbitTemplate.convertAndSend(exchangeToShop, routing, checkOrderPickupPointCommand);
    }

    @Override
    public void produceCheckOrderShopCommand(CheckOrderShopCommand checkOrderShopCommand) {
        log.info("IN: try to produce a message = {}", checkOrderShopCommand.getClass().getSimpleName());
        String routing = routingPrefixToShop + checkShopPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToShop, routing);
        rabbitTemplate.convertAndSend(exchangeToShop, routing, checkOrderShopCommand);
    }

    @Override
    public void produceCheckOrderUserCommand(CheckOrderUserCommand checkOrderUserCommand) {
        log.info("IN: try to produce a message = {}", checkOrderUserCommand.getClass().getSimpleName());
        String routing = routingPrefixToUser + checkUserPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToUser, routing);
        rabbitTemplate.convertAndSend(exchangeToUser, routing, checkOrderUserCommand);
    }

    @Override
    public void produceFailOrderCommand(FailOrderCommand failOrderCommand) {
        log.info("IN: try to produce a message = {}", failOrderCommand.getClass().getSimpleName());
        String routing = routingPrefixToOrder + failOrderRoutingKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToOrder, routing);
        rabbitTemplate.convertAndSend(exchangeToOrder, routing, failOrderCommand);
    }
}