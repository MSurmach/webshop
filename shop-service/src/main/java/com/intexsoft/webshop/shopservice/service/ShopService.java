package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.shopservice.dto.ShopDto;

import java.util.List;

public interface ShopService {

    ShopDto createShop(ShopDto userDto);

    List<ShopDto> findShopByNameOrEmail(ShopDto shopDto);
}