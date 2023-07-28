package com.intexsoft.webshop.productservice.service;

import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;

public interface ShopEventConsumer {
    void receive(ShopCreatedEvent shopCreatedEvent);
}
