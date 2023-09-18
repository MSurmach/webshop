package com.intexsoft.webshop.productservicekt.producer

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent

interface ProductEventProducer {
    fun produceProductEventCreated(productCreatedEvent: ProductCreatedEvent)
    fun produceProductEventDeleted(productDeletedEvent: ProductDeletedEvent)
    fun produceProductEventUpdated(productUpdatedEvent: ProductUpdatedEvent)
    fun produceProductOrderQuantityIncremented(productOrderQuantityIncrementedEvent: ProductOrderQuantityIncrementedEvent)
}