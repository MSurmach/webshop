package com.intexsoft.webshop.shopservice.controller;

import com.intexsoft.webshop.shopservice.dto.ShopProductLinkCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopProductLinkDto;
import com.intexsoft.webshop.shopservice.service.ShopProductLinkService;
import com.intexsoft.webshop.shopservice.util.JsonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopProductLink")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShopProductLinkController {
    private final ShopProductLinkService shopProductLinkService;

    @PostMapping
    public ResponseEntity<ShopProductLinkDto> createShopProductLink(
            @RequestBody @Valid ShopProductLinkCreateDto shopProductLinkCreateDto) {
        log.info("IN: request to create a new link between product with id = {} and shop with id = {} received. " +
                "Request body = {}", shopProductLinkCreateDto.getProductId(), shopProductLinkCreateDto.getShopId(),
                JsonUtils.getAsString(shopProductLinkCreateDto));
        ShopProductLinkDto createdShopProductLink = shopProductLinkService.createShopProductLink(shopProductLinkCreateDto);
        log.info("OUT: new link between product with id = {} and shop with id = {} created successfully." +
                        " Response body = {}", createdShopProductLink.getProductId(), createdShopProductLink.getShopId(),
                JsonUtils.getAsString(createdShopProductLink));
        return ResponseEntity.ok(createdShopProductLink);
    }
}