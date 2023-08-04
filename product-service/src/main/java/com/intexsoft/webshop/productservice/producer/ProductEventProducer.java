package com.intexsoft.webshop.productservice.producer;

import com.intexsoft.webshop.messagecommon.event.product.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.ProductUpdatedEvent;

public interface ProductEventProducer {
    void produceProductEventCreated(ProductCreatedEvent productCreatedEvent);

    void produceProductEventDeleted(ProductDeletedEvent productDeletedEvent);

    void produceProductEventUpdated(ProductUpdatedEvent productUpdatedEvent);
}
