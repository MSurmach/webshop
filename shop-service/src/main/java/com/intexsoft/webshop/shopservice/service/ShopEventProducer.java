package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;

public interface ShopEventProducer {
    void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent);
}
