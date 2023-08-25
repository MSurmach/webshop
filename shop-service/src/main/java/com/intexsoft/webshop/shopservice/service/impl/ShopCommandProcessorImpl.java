package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.OrderRequestToShopCommand;
import com.intexsoft.webshop.shopservice.exception.ShopNotFoundException;
import com.intexsoft.webshop.shopservice.mapper.ShopEventMapper;
import com.intexsoft.webshop.shopservice.mapper.ShopMapper;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.webshop.shopservice.producer.ShopEventProducer;
import com.intexsoft.webshop.shopservice.repository.PickupPointRepository;
import com.intexsoft.webshop.shopservice.repository.ShopProductLinkRepository;
import com.intexsoft.webshop.shopservice.repository.ShopRepository;
import com.intexsoft.webshop.shopservice.service.ShopCommandProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopCommandProcessorImpl implements ShopCommandProcessor {
    private final ShopRepository shopRepository;
    private final ShopProductLinkRepository shopProductLinkRepository;
    private final PickupPointRepository pickupPointRepository;
    private final ShopEventProducer shopEventProducer;
    private final ShopEventMapper shopEventMapper;
    private final ShopMapper shopMapper;

    @Override
    public void checkShop(CheckOrderShopCommand checkOrderShopCommand) {
        Long shopId = checkOrderShopCommand.getShopId();
        log.info("IN: try to check shop with id = {}", shopId);
        boolean isShopExists = shopRepository.existsById(shopId);
        log.info("OUT: check shop with id = {} result is {}",
                shopId, isShopExists);
        shopEventProducer.produceShopCheckedEvent(shopEventMapper.toShopCheckedEvent(isShopExists,
                checkOrderShopCommand));
    }

    @Override
    public void checkPickupPoint(CheckOrderPickupPointCommand checkOrderPickupPointCommand) {
        Long pickupPointId = checkOrderPickupPointCommand.getPickupPointId();
        Long shopId = checkOrderPickupPointCommand.getShopId();
        log.info("IN: try to check pickup point with id = {} for shop with id = {}", pickupPointId, shopId);
        boolean isPickupPointExists = pickupPointRepository.existsShopByIdAndByPickupPointId(shopId, pickupPointId);
        log.info("OUT: check pickup point with id = {} result is {}",
                pickupPointId, isPickupPointExists);
        shopEventProducer.producePickupPointCheckedEvent(shopEventMapper.toPickupPointCheckedEvent(isPickupPointExists,
                checkOrderPickupPointCommand));
    }

    @Override
    public void checkProduct(CheckOrderProductCommand checkOrderProductCommand) {
        Long shopId = checkOrderProductCommand.getShopId();
        Set<Long> orderProductIds = checkOrderProductCommand.getOrderProductDetails().stream()
                .map(CheckOrderProductCommand.OrderProductDetail::getProductId)
                .collect(Collectors.toSet());
        log.info("IN: try to check products with ids in = {} for shop with id = {}", orderProductIds, shopId);
        boolean isProductShopLinkExists = true;
        for (CheckOrderProductCommand.OrderProductDetail orderProductDetail : checkOrderProductCommand.getOrderProductDetails()) {
            isProductShopLinkExists = shopProductLinkRepository.existsByShopIdAndProductIdAndQuantityLessThanAndPriceEquals(shopId,
                    orderProductDetail.getProductId(), orderProductDetail.getQuantity(), orderProductDetail.getProductPrice());
            if (!isProductShopLinkExists) break;
        }
        log.info("OUT: check products with id = {} for shop with id = {} result is {}", orderProductIds, shopId, isProductShopLinkExists);
        shopEventProducer.produceOrderProductCheckedEvent(shopEventMapper.toOrderProductCheckedEvent(isProductShopLinkExists, checkOrderProductCommand));
    }

    @Override
    @Transactional
    public void addOrderRequest(OrderRequestToShopCommand orderRequestToShopCommand) {
        Long shopId = orderRequestToShopCommand.getShopId();
        try {
            Shop foundShop = shopRepository.findById(shopId).orElseThrow(
                    () -> new ShopNotFoundException("Shop with id = " + shopId + " not found"));
            foundShop.addOrderRequest(shopMapper.toShopOrderRequest(orderRequestToShopCommand));
            shopEventProducer.produceOrderRequestToShopAddedEvent(
                    shopEventMapper.toOrderRequestToShopAddedEvent(true, orderRequestToShopCommand));
        } catch (ShopNotFoundException shopNotFoundException) {
            log.error("Unable to add order request to shop with id = {}. Reason: {}",
                    shopId, shopNotFoundException.getMessage());
            shopEventProducer.produceOrderRequestToShopAddedEvent(
                    shopEventMapper.toOrderRequestToShopAddedEvent(false, orderRequestToShopCommand));
        }
    }
}