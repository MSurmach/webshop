package com.intexsoft.webshop.productqueryservice.consumer;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopProductLinkCreatedEvent;

public interface ShopEventConsumer {
    void receiveShopProductLinkCreatedEvent(ShopProductLinkCreatedEvent shopProductLinkCreatedEvent);
}