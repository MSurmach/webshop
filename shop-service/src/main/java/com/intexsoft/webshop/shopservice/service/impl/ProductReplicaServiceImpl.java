package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.shopservice.mapper.ProductReplicaMapper;
import com.intexsoft.webshop.shopservice.model.ProductReplica;
import com.intexsoft.webshop.shopservice.repository.ProductReplicaRepository;
import com.intexsoft.webshop.shopservice.service.ProductReplicaService;
import com.intexsoft.webshop.shopservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductReplicaServiceImpl implements ProductReplicaService {
    private final ProductReplicaRepository productReplicaRepository;
    private final ProductReplicaMapper productReplicaMapper;

    @Override
    public void saveProductFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        ProductReplica productReplicaFromEvent = productReplicaMapper.toProductReplica(productCreatedEvent);
        log.info("IN: trying to save new product = {} from {}",
                JsonUtils.getAsString(productReplicaFromEvent), productCreatedEvent.getClass().getSimpleName());
        ProductReplica savedProductReplica = productReplicaRepository.save(productReplicaFromEvent);
        log.info("OUT: product with id = {} saved successfully", savedProductReplica.getId());
    }
}