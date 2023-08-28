package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.messagecommon.command.ChangeProductOrderQuantityType;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.productservice.mapper.ProductEventMapper;
import com.intexsoft.webshop.productservice.producer.ProductEventProducer;
import com.intexsoft.webshop.productservice.repository.ProductRepository;
import com.intexsoft.webshop.productservice.service.ProductCommandProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCommandProcessorImpl implements ProductCommandProcessor {
    private final ProductRepository productRepository;
    private final ProductEventProducer productEventProducer;
    private final ProductEventMapper productEventMapper;

    @Override
    @Transactional
    public void processChangeProductOrderQuantityCommand(ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand) {
        ChangeProductOrderQuantityType changeType = changeProductOrderQuantityCommand.getChangeType();
        Map<Long, Short> productIdDeltaOrderQuantityMap = changeProductOrderQuantityCommand.getProductIdDeltaOrderQuantityMap();
        switch (changeType) {
            case INCREMENT ->
                    incrementOrderQuantityForProducts(productIdDeltaOrderQuantityMap, changeProductOrderQuantityCommand.getOrderId());
            case DECREMENT -> decrementOrderQuantityForProducts(productIdDeltaOrderQuantityMap);
        }
    }

    private void incrementOrderQuantityForProducts(Map<Long, Short> productIdIncrementDeltaOrderQuantityMap, Long orderId) {
        log.info("IN: trying to increment a quantity of orders for products using map of productId and increment delta." +
                " Map size = {}, content = {}", productIdIncrementDeltaOrderQuantityMap.size(), productIdIncrementDeltaOrderQuantityMap);
        try {
            productIdIncrementDeltaOrderQuantityMap.forEach(
                    (productId, incrementDelta) ->
                            productRepository.updateOrderQuantityByIncrementAndProductId(incrementDelta, productId)
            );
            log.info("OUT: all products are incremented");
            productEventProducer.produceProductOrderQuantityIncremented(
                    productEventMapper.toProductOrderQuantityIncrementedEvent(
                            productIdIncrementDeltaOrderQuantityMap.keySet(),true, orderId));
        } catch (RuntimeException exception) {
            log.error("Unable to increment order quantity for products. Exception message: {}", exception.getMessage());
            productEventProducer.produceProductOrderQuantityIncremented(
                    productEventMapper.toProductOrderQuantityIncrementedEvent(
                            productIdIncrementDeltaOrderQuantityMap.keySet(),false, orderId));
        }
    }

    private void decrementOrderQuantityForProducts(Map<Long, Short> productIdDecrementDeltaOrderQuantityMap) {
        log.info("IN: trying to decrement a quantity of orders for products using map of productId and decrement delta." +
                " Map size = {}, content = {}", productIdDecrementDeltaOrderQuantityMap.size(), productIdDecrementDeltaOrderQuantityMap);
        productIdDecrementDeltaOrderQuantityMap.forEach(
                (productId, decrementDelta) ->
                        productRepository.updateOrderQuantityByDecrementAndProductId(decrementDelta, productId)
        );
        log.info("OUT: all products are decremented");
    }
}