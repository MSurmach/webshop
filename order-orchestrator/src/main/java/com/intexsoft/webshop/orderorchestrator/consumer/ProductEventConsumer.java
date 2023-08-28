package com.intexsoft.webshop.orderorchestrator.consumer;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;

public interface ProductEventConsumer {
    void receiveProductOrderQuantityIncrementedEvent(ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent);
}