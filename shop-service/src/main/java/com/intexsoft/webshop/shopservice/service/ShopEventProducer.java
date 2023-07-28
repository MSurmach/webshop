package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;

public interface ShopEventProducer {
    void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent);
}
