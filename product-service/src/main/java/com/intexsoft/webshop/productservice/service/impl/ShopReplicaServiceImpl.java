package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.productservice.mapper.ProductApiMapper;
import com.intexsoft.webshop.productservice.model.ShopReplica;
import com.intexsoft.webshop.productservice.repository.ShopReplicaRepository;
import com.intexsoft.webshop.productservice.service.ShopReplicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopReplicaServiceImpl implements ShopReplicaService {
    private final ShopReplicaRepository shopReplicaRepository;
    private final ProductApiMapper productApiMapper;

    @Override
    public ShopReplicaDto createShopReplica(ShopReplicaDto shopReplicaDto) {
        ShopReplica savedShopReplica = shopReplicaRepository.save(productApiMapper.toShopReplica(shopReplicaDto));
        return productApiMapper.toShopReplicaDto(savedShopReplica);
    }
}