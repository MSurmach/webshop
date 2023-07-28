package com.intexsoft.webshop.shopservice.service.impl;

import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.exception.SuchShopExistsException;
import com.intexsoft.webshop.shopservice.mapper.ShopMapper;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.webshop.shopservice.repository.ShopRepository;
import com.intexsoft.webshop.shopservice.service.ShopEventProducer;
import com.intexsoft.webshop.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final ShopMapper shopMapper;
    private final ShopEventProducer shopEventProducer;

    @Override
    @Transactional
    public ShopDto createShop(ShopCreateDto shopCreateDto) {
        String shopName = shopCreateDto.getName();
        String shopEmail = shopCreateDto.getEmail();
        List<ShopDto> foundedShops = findShopByNameOrEmail(shopName, shopEmail);
        if (!foundedShops.isEmpty()) {
            StringBuilder exceptionMessageBuilder = new StringBuilder("Unable to save a new shop:");
            foundedShops.forEach(existedShop -> {
                if (Objects.equals(existedShop.getName().toLowerCase(), shopName.toLowerCase()))
                    exceptionMessageBuilder.append(" such shop name exists;");
                if (Objects.equals(existedShop.getEmail().toLowerCase(), shopEmail.toLowerCase()))
                    exceptionMessageBuilder.append(" such shop email exists;");
            });
            throw new SuchShopExistsException(exceptionMessageBuilder.toString());
        }
        Shop savedShop = shopRepository.save(shopMapper.toShop(shopCreateDto));
        shopEventProducer.produceShopCreatedEvent(shopMapper.toShopCreatedEvent(savedShop));
        return shopMapper.toShopDto(savedShop);
    }

    private List<ShopDto> findShopByNameOrEmail(String name, String email) {
        List<ShopDto> foundedShops = shopRepository.
                findShopByNameIgnoreCaseOrEmailIgnoreCase(name, email)
                .stream()
                .map(shopMapper::toShopDto)
                .collect(Collectors.toList());
        return foundedShops;
    }
}