package com.intexsoft.webshop.productservice.producer;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent;

public interface ProductEventProducer {
    void produceProductEventCreated(ProductCreatedEvent productCreatedEvent);

    void produceProductEventDeleted(ProductDeletedEvent productDeletedEvent);

    void produceProductEventUpdated(ProductUpdatedEvent productUpdatedEvent);

    void produceProductOrderQuantityIncremented(ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent);
}