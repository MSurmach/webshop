package com.intexsoft.webshop.shopservice.controller;

import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.intexsoft.webshop.shopservice.util.JsonUtils.getAsString;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopDto> createUser(@RequestBody @Valid ShopCreateDto shopCreateDto) {
        log.info("IN: request to create a new shop received. Request body = {}",
                getAsString(shopCreateDto));
        ShopDto createdShopDto = shopService.createShop(shopCreateDto);
        log.info("OUT: new shop created successfully. Response body = {}",
                getAsString(createdShopDto));
        return ResponseEntity.ok(createdShopDto);
    }
}