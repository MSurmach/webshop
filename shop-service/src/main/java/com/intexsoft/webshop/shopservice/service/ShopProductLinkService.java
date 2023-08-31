package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.shopservice.dto.ShopProductLinkCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopProductLinkDto;

public interface ShopProductLinkService {

    ShopProductLinkDto createShopProductLink(ShopProductLinkCreateDto shopProductLinkCreateDto);
}