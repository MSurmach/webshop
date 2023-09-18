package com.intexsoft.webshop.orderservicekt.producer

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent

interface OrderEventProducer {
    fun produceOrderInitializedEvent(orderInitializedEvent: OrderInitializedEvent)
}
