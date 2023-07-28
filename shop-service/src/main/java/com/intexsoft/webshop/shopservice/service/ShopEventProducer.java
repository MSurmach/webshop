package com.intexsoft.webshop.shopservice.service;

public interface ShopEventProducer {
    void produceEvent(String routingKey, Object object);
}
