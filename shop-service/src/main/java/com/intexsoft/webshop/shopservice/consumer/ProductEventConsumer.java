package com.intexsoft.webshop.shopservice.consumer;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;

public interface ProductEventConsumer {
    void receiveProductCreatedEvent(ProductCreatedEvent productCreatedEvent);
}
