package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;

public interface ShopService {

    ShopDto createShop(ShopCreateDto shopCreateDto);
}