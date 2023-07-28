package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;

public interface ShopReplicaService {
    ShopReplicaDto createShopReplica(ShopCreatedEvent shopCreatedEvent);
}
