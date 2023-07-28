package com.intexsoft.webshop.userservice.service;

public interface UserEventProducer {
    void produceEvent(String routingKey, Object object);
}
