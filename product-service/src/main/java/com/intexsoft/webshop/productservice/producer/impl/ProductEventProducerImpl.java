package com.intexsoft.webshop.productservice.producer.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent;
import com.intexsoft.webshop.productservice.producer.ProductEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEventProducerImpl implements ProductEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.product.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.product.routing-keys.product_created}")
    private String productCreatedRoutingKey;

    @Value("${rmq.event.product.routing-keys.product_deleted}")
    private String productDeletedRoutingKey;

    @Value("${rmq.event.product.routing-keys.product_updated}")
    private String productUpdatedRoutingKey;
    @Value("${rmq.event.product.routing-keys.product_order_quantity_incremented}")
    private String productOrderQuantityIncrementedRoutingKey;

    @Override
    public void produceProductEventCreated(ProductCreatedEvent productCreatedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                productCreatedEvent.getClass().getSimpleName(), getAsString(productCreatedEvent));
        String routing = routingPrefix + productCreatedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, productCreatedEvent);
    }

    @Override
    public void produceProductEventDeleted(ProductDeletedEvent productDeletedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                productDeletedEvent.getClass().getSimpleName(), getAsString(productDeletedEvent));
        String routing = routingPrefix + productDeletedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, productDeletedEvent);
    }

    @Override
    public void produceProductEventUpdated(ProductUpdatedEvent productUpdatedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                productUpdatedEvent.getClass().getSimpleName(), getAsString(productUpdatedEvent));
        String routing = routingPrefix + productUpdatedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, productUpdatedEvent);
    }

    @Override
    public void produceProductOrderQuantityIncremented(ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                productOrderQuantityIncrementedEvent.getClass().getSimpleName(), getAsString(productOrderQuantityIncrementedEvent));
        String routing = routingPrefix + productOrderQuantityIncrementedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, productOrderQuantityIncrementedEvent);
    }
}