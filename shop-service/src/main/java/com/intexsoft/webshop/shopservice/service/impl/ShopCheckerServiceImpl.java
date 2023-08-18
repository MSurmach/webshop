package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.shopservice.mapper.ShopEventMapper;
import com.intexsoft.webshop.shopservice.producer.ShopEventProducer;
import com.intexsoft.webshop.shopservice.repository.PickupPointRepository;
import com.intexsoft.webshop.shopservice.repository.ShopRepository;
import com.intexsoft.webshop.shopservice.service.ShopCheckerService;
import com.intexsoft.webshop.shopservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopCheckerServiceImpl implements ShopCheckerService {
    private final ShopRepository shopRepository;
    private final PickupPointRepository pickupPointRepository;
    private final ShopEventProducer shopEventProducer;
    private final ShopEventMapper shopEventMapper;

    @Override
    public void checkShop(CheckOrderShopCommand checkOrderShopCommand) {
        Long shopId = checkOrderShopCommand.getShopId();
        log.info("IN: try to check shop with id = {}. Message payload = {}",
                shopId, JsonUtils.getAsString(checkOrderShopCommand));
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
        log.info("IN: try to check pickup point with id = {} for shop with id = {}. Message payload = {}",
                pickupPointId, shopId, JsonUtils.getAsString(checkOrderPickupPointCommand));
        boolean isPickupPointExists = pickupPointRepository.existsShopByIdAndByPickupPointId(shopId, pickupPointId);
        log.info("OUT: check pickup point with id = {} result is {}",
                pickupPointId, isPickupPointExists);
        shopEventProducer.producePickupPointCheckedEvent(shopEventMapper.toPickupPointCheckedEvent(isPickupPointExists,
                checkOrderPickupPointCommand));
    }
}