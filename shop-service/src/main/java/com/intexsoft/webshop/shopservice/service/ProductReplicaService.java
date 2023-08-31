package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;

public interface ProductReplicaService {
    void saveProductFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent);
}