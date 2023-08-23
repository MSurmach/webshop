package com.intexsoft.webshop.orderservice.producer.impl;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderservice.producer.OrderEventProducer;
import com.intexsoft.webshop.orderservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducerImpl implements OrderEventProducer {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange eventExchange;
    @Value("${rmq.event.order.routing-prefix}")
    private String routingPrefix;
    @Value("${rmq.event.order.routing-keys.order_initialized}")
    private String orderInitializedRoutingKey;

    @Override
    public void produceOrderInitializedEvent(OrderInitializedEvent orderInitializedEvent) {
        log.info("IN: produce {} message. The event message = {}",
                orderInitializedEvent.getClass().getName(), JsonUtils.getAsString(orderInitializedEvent));
        String routing = routingPrefix + orderInitializedRoutingKey;
        log.debug("Message is producing to exchange = {}, with routing = {}", eventExchange.getName(), routing);
        rabbitTemplate.convertAndSend(eventExchange.getName(), routing, orderInitializedEvent);
    }
}