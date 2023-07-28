package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;

import java.util.List;

public interface ShopService {

    ShopDto createShop(ShopCreateDto shopCreateDto);
}