package com.intexsoft.webshop.orderservicekt.producer.impl

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent
import com.intexsoft.webshop.orderservicekt.log
import com.intexsoft.webshop.orderservicekt.producer.OrderEventProducer
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OrderEventProducerImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val eventExchange: TopicExchange
) : OrderEventProducer {
    @Value("\${rmq.event.order.routing-prefix}")
    private lateinit var routingPrefix: String

    @Value("\${rmq.event.order.routing-keys.order_initialized}")
    private lateinit var orderInitializedRoutingKey: String
    override fun produceOrderInitializedEvent(orderInitializedEvent: OrderInitializedEvent) {
        log.info("IN: produce ${orderInitializedEvent::class.simpleName} message. The event message = $orderInitializedEvent")
        val routing = routingPrefix + orderInitializedRoutingKey
        log.debug("Message is producing to exchange = ${eventExchange.name}, with routing = $routing")
        rabbitTemplate.convertAndSend(eventExchange.name, routing, orderInitializedEvent)
    }
}