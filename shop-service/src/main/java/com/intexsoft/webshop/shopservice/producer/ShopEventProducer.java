package com.intexsoft.webshop.shopservice.producer;

import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCreatedEvent;

public interface ShopEventProducer {
    void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent);

    void produceShopCheckedEvent(ShopCheckedEvent shopCheckedEvent);
    void producePickupPointCheckedEvent(PickupPointCheckedEvent pickupPointCheckedEvent);
}
