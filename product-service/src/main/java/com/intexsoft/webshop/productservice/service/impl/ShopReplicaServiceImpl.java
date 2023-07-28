package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.productservice.mapper.ShopReplicaMapper;
import com.intexsoft.webshop.productservice.model.ShopReplica;
import com.intexsoft.webshop.productservice.repository.ShopReplicaRepository;
import com.intexsoft.webshop.productservice.service.ShopReplicaService;
import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopReplicaServiceImpl implements ShopReplicaService {
    private final ShopReplicaRepository shopReplicaRepository;
    private final ShopReplicaMapper shopReplicaMapper;

    @Override
    public ShopReplicaDto createShopReplica(ShopCreatedEvent shopCreatedEvent) {
        ShopReplica savedShopReplica = shopReplicaRepository.save(shopReplicaMapper.toShopReplica(shopCreatedEvent));
        return shopReplicaMapper.toShopReplicaDto(savedShopReplica);
    }
}