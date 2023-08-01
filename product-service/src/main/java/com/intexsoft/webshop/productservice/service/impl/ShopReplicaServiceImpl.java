package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.productservice.mapper.ShopReplicaMapper;
import com.intexsoft.webshop.productservice.model.ShopReplica;
import com.intexsoft.webshop.productservice.repository.ShopReplicaRepository;
import com.intexsoft.webshop.productservice.service.ShopReplicaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopReplicaServiceImpl implements ShopReplicaService {
    private final ShopReplicaRepository shopReplicaRepository;
    private final ShopReplicaMapper shopReplicaMapper;

    @Override
    public ShopReplicaDto createShopReplica(ShopCreatedEvent shopCreatedEvent) {
        log.info("IN: trying to save a new shop replica by the event message  = {}", getAsString(shopCreatedEvent));
        ShopReplica savedShopReplica = shopReplicaRepository.save(shopReplicaMapper.toShopReplica(shopCreatedEvent));
        log.info("OUT: new shop replica saved successfully. Saved shop replica details = {}", getAsString(savedShopReplica));
        return shopReplicaMapper.toShopReplicaDto(savedShopReplica);
    }
}