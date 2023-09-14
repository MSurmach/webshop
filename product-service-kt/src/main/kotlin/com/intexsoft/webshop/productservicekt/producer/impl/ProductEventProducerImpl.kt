package com.intexsoft.webshop.productservicekt.producer.impl

import com.intexsoft.webshop.messagecommon.event.BaseEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.producer.ProductEventProducer
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ProductEventProducerImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val eventExchange: TopicExchange
) : ProductEventProducer {


    @Value("\${rmq.event.product.routing-prefix}")
    private lateinit var routingPrefix: String

    @Value("\${rmq.event.product.routing-keys.product_created}")
    private lateinit var productCreatedRoutingKey: String

    @Value("\${rmq.event.product.routing-keys.product_deleted}")
    private lateinit var productDeletedRoutingKey: String

    @Value("\${rmq.event.product.routing-keys.product_updated}")
    private lateinit var productUpdatedRoutingKey: String

    @Value("\${rmq.event.product.routing-keys.product_order_quantity_incremented}")
    private lateinit var productOrderQuantityIncrementedRoutingKey: String
    override fun produceProductEventCreated(productCreatedEvent: ProductCreatedEvent) {
        val routing = routingPrefix + productCreatedRoutingKey
        produceEventWithRouting(productCreatedEvent, routing)
    }

    override fun produceProductEventDeleted(productDeletedEvent: ProductDeletedEvent) {
        val routing = routingPrefix + productDeletedRoutingKey
        produceEventWithRouting(productDeletedEvent, routing)
    }

    override fun produceProductEventUpdated(productUpdatedEvent: ProductUpdatedEvent) {
        val routing = routingPrefix + productUpdatedRoutingKey
        produceEventWithRouting(productUpdatedEvent, routing)
    }

    override fun produceProductOrderQuantityIncremented(productOrderQuantityIncrementedEvent: ProductOrderQuantityIncrementedEvent) {
        val routing = routingPrefix + productOrderQuantityIncrementedRoutingKey
        produceEventWithRouting(productOrderQuantityIncrementedEvent, routing)
    }

    private fun produceEventWithRouting(baseEvent: BaseEvent, routing: String) {
        log.info("IN: produce ${baseEvent::class.simpleName} message. The event message = $baseEvent")
        log.debug("Message is producing to exchange = ${eventExchange.name}, with routing = $routing")
        rabbitTemplate.convertAndSend(eventExchange.name, routing, baseEvent)
    }
}