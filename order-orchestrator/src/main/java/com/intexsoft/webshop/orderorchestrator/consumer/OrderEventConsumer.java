package com.intexsoft.webshop.orderorchestrator.consumer;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;

public interface OrderEventConsumer {
    void receiveOrderInitializedEvent(OrderInitializedEvent orderInitializedEvent);
}
