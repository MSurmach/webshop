package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.shopservice.dto.ShopProductLinkCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopProductLinkDto;
import com.intexsoft.webshop.shopservice.exception.ProductReplicaNotFoundException;
import com.intexsoft.webshop.shopservice.exception.ShopNotFoundException;
import com.intexsoft.webshop.shopservice.mapper.ShopEventMapper;
import com.intexsoft.webshop.shopservice.mapper.ShopProductLinkMapper;
import com.intexsoft.webshop.shopservice.model.ProductReplica;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.webshop.shopservice.model.ShopProductLink;
import com.intexsoft.webshop.shopservice.producer.ShopEventProducer;
import com.intexsoft.webshop.shopservice.repository.ProductReplicaRepository;
import com.intexsoft.webshop.shopservice.repository.ShopProductLinkRepository;
import com.intexsoft.webshop.shopservice.repository.ShopRepository;
import com.intexsoft.webshop.shopservice.service.ShopProductLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.intexsoft.webshop.shopservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopProductLinkServiceImpl implements ShopProductLinkService {
    private final ShopRepository shopRepository;
    private final ProductReplicaRepository productReplicaRepository;
    private final ShopProductLinkRepository shopProductLinkRepository;
    private final ShopProductLinkMapper shopProductLinkMapper;
    private final ShopEventProducer shopEventProducer;
    private final ShopEventMapper shopEventMapper;

    @Override
    @Transactional
    public ShopProductLinkDto createShopProductLink(ShopProductLinkCreateDto shopProductLinkCreateDto) {
        Long productId = shopProductLinkCreateDto.getProductId();
        Long shopId = shopProductLinkCreateDto.getShopId();
        log.info("IN: trying to save a new link between product with id = {} and shop with id = {}." +
                " The link details = {}", productId, shopId, getAsString(shopProductLinkCreateDto));
        ProductReplica foundProduct = productReplicaRepository.findById(productId)
                .orElseThrow(() -> new ProductReplicaNotFoundException("Unable to create product to shop link," +
                        " the product with id = " + productId + " not found"));
        Shop foundShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException("Unable to create product to shop link," +
                        " the shop with id = " + shopId + " not found"));
        ShopProductLink savedShopProductLink = shopProductLinkRepository.save(
                shopProductLinkMapper.toShopProductLink(foundShop, foundProduct, shopProductLinkCreateDto));
        ShopProductLinkDto shopProductLinkDto = shopProductLinkMapper.toShopProductLinkDto(savedShopProductLink);
        log.info("OUT: new new link between product with id = {} and shop with id = {} saved successfully.",
                productId, shopId);
        shopEventProducer.produceShopProductLinkCreatedEvent(
                shopEventMapper.toShopProductLinkCreatedEvent(savedShopProductLink, foundShop.getName()));
        return shopProductLinkDto;
    }
}