package com.intexsoft.webshop.shopservice.controller;

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

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopDto> createUser(@RequestBody @Valid ShopDto shopDto) {
        ShopDto createdShopDto = shopService.createShop(shopDto);
        return ResponseEntity.ok(createdShopDto);
    }
}