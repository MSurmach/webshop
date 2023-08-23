package com.intexsoft.webshop.orderorchestrator.consumer;

import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;

public interface ShopEventConsumer {
    void receiveShopCheckedEvent(ShopCheckedEvent shopCheckedEvent);

    void receivePickupPointCheckedEvent(PickupPointCheckedEvent pickupPointCheckedEvent);
}
