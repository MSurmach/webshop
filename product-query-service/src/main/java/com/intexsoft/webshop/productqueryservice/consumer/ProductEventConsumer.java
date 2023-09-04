package com.intexsoft.webshop.productqueryservice.consumer;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;

public interface ProductEventConsumer {
    void receiveProductCreatedEvent(ProductCreatedEvent productCreatedEvent);
}
