package com.intexsoft.webshop.shopservice.producer;

import com.intexsoft.webshop.messagecommon.event.shop.impl.*;

public interface ShopEventProducer {
    void produceShopCreatedEvent(ShopCreatedEvent shopCreatedEvent);

    void produceShopCheckedEvent(ShopCheckedEvent shopCheckedEvent);
    void producePickupPointCheckedEvent(PickupPointCheckedEvent pickupPointCheckedEvent);
    void produceOrderProductCheckedEvent(ProductCheckedEvent productCheckedEvent);
    void produceOrderRequestToShopAddedEvent(OrderRequestToShopAddedEvent orderRequestToShopAddedEvent);
}