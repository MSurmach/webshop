package com.intexsoft.webshop.orderorchestrator.producer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.*;
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
    @Value("${rmq.command.shop.routing-keys.check_product}")
    private String checkProductPrefixKey;
    @Value("${rmq.command.shop.routing-keys.request_order}")
    private String requestOrderPrefixKey;
    //to user-service
    @Value("${rmq.command.user.exchange}")
    private String exchangeToUser;
    @Value("${rmq.command.user.routing-prefix}")
    private String routingPrefixToUser;
    @Value("${rmq.command.user.routing-keys.check_user}")
    private String checkUserPrefixKey;
    //to product-service
    @Value("${rmq.command.product.exchange}")
    private String exchangeToProduct;
    @Value("${rmq.command.product.routing-prefix}")
    private String routingPrefixToProduct;
    @Value("${rmq.command.product.routing-keys.increment_order_quantity}")
    private String incrementProductOrderQuantityPrefixKey;

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

    @Override
    public void produceCheckOrderProductCommand(CheckOrderProductCommand checkOrderProductCommand) {
        log.info("IN: try to produce a message = {}", checkOrderProductCommand.getClass().getSimpleName());
        String routing = routingPrefixToShop + checkProductPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToShop, routing);
        rabbitTemplate.convertAndSend(exchangeToShop, routing, checkOrderProductCommand);
    }

    @Override
    public void produceChangeProductOrderQuantityCommand(ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand) {
        log.info("IN: try to produce a message = {}", changeProductOrderQuantityCommand.getClass().getSimpleName());
        String routing = routingPrefixToProduct + incrementProductOrderQuantityPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToProduct, routing);
        rabbitTemplate.convertAndSend(exchangeToProduct, routing, changeProductOrderQuantityCommand);
    }

    @Override
    public void produceOrderRequestToShopCommand(OrderRequestToShopCommand orderRequestToShopCommand) {
        log.info("IN: try to produce a message = {}", orderRequestToShopCommand.getClass().getSimpleName());
        String routing = routingPrefixToShop + requestOrderPrefixKey;
        log.info("OUT: the message is producing to exchange = {}, with routing = {}", exchangeToShop, routing);
        rabbitTemplate.convertAndSend(exchangeToShop, routing, orderRequestToShopCommand);
    }
}