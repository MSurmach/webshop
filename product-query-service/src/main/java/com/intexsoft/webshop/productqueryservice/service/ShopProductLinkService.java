package com.intexsoft.webshop.productqueryservice.service;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopProductLinkCreatedEvent;

public interface ShopProductLinkService {
    void saveShopProductLinkFromShopProductLinkCreatedEvent(ShopProductLinkCreatedEvent shopProductLinkCreatedEvent);
}