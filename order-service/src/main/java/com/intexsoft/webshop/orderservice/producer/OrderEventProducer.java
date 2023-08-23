package com.intexsoft.webshop.orderservice.producer;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;

public interface OrderEventProducer {
    void produceOrderInitializedEvent(OrderInitializedEvent orderInitializedEvent);
}
