package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.exception.SuchShopExistsException;
import com.intexsoft.webshop.shopservice.mapper.ShopApiMapper;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.webshop.shopservice.repository.ShopRepository;
import com.intexsoft.webshop.shopservice.service.ShopEventProducer;
import com.intexsoft.webshop.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ShopApiMapper shopApiMapper;
    private final ShopEventProducer shopEventProducer;
    @Value("${rmq.event.shop.routing-keys.shop_created}")
    private String createdShopRoutingKey;

    @Override
    @Transactional
    public ShopDto createShop(ShopDto shopDto) {
        List<ShopDto> foundedShops = findShopByNameOrEmail(shopDto);
        if (!foundedShops.isEmpty()) {
            StringBuilder exceptionMessageBuilder = new StringBuilder("Unable to save a new shop:");
            foundedShops.forEach(existedShop -> {
                if (Objects.equals(existedShop.getName().toLowerCase(), shopDto.getName().toLowerCase()))
                    exceptionMessageBuilder.append(" such shop name exists;");
                if (Objects.equals(existedShop.getEmail().toLowerCase(), shopDto.getEmail().toLowerCase()))
                    exceptionMessageBuilder.append(" such shop email exists;");
            });
            throw new SuchShopExistsException(exceptionMessageBuilder.toString());
        }
        Shop savedShop = shopRepository.save(shopApiMapper.toShop(shopDto));
        shopEventProducer.produceEvent(createdShopRoutingKey, shopApiMapper.toShopCreatedEvent(savedShop));
        return shopApiMapper.toShopDto(savedShop);
    }

    @Override
    public List<ShopDto> findShopByNameOrEmail(ShopDto shopDto) {
        List<ShopDto> foundedShops = shopRepository.
                findShopByNameIgnoreCaseOrEmailIgnoreCase(shopDto.getName(), shopDto.getEmail())
                .stream()
                .map(shopApiMapper::toShopDto)
                .collect(Collectors.toList());
        return foundedShops;
    }
}