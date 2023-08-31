package com.intexsoft.webshop.productqueryservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.exception.notfound404.ProductNotFoundException;
import com.intexsoft.webshop.productqueryservice.mapper.ProductEventMapper;
import com.intexsoft.webshop.productqueryservice.mapper.ProductMapper;
import com.intexsoft.webshop.productqueryservice.model.Product;
import com.intexsoft.webshop.productqueryservice.model.ShopProductLink;
import com.intexsoft.webshop.productqueryservice.repository.ProductRepository;
import com.intexsoft.webshop.productqueryservice.repository.ShopProductLinkRepository;
import com.intexsoft.webshop.productqueryservice.service.ProductQueryService;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    private final ShopProductLinkRepository shopProductLinkRepository;
    private final ProductEventMapper productEventMapper;
    private final ProductMapper productMapper;

    @Override
    public void saveProductFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        Product productFromEvent = productEventMapper.toProduct(productCreatedEvent);
        log.info("IN: trying to save new product = {} from {}",
                JsonUtils.getAsString(productFromEvent), productCreatedEvent.getClass().getSimpleName());
        Product savedProduct = productRepository.save(productFromEvent);
        log.info("OUT: product with id = {} saved successfully", savedProduct.getId());
    }

    @Override
    public List<ProductDto> findProducts(Pageable pageable) {
        log.info("IN: trying to find products. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Product> products = productRepository
                .findAll(pageable)
                .getContent();
        log.info("OUT: {} products found", products.size());
        return productMapper.toProductDtos(products);
    }

    @Override
    public ProductDto findProductById(Long productId) {
        log.info("IN: trying to find a product by id = {}", productId);
        Product foundProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        log.info("OUT: the product with id = {} found successfully. Found product details = {}",
                productId, JsonUtils.getAsString(foundProduct));
        return productMapper.toProductDto(foundProduct);
    }

    @Override
    public List<ProductDto> findProductsByShopId(Pageable pageable, Long shopId) {
        log.info("IN: trying to find products that belong to shop with id = {}. Page size = {}, page number = {}",
                shopId, pageable.getPageSize(), pageable.getPageNumber());
        Set<Long> productIds = shopProductLinkRepository.findAllByShopId(shopId).stream()
                .map(ShopProductLink::getProductId)
                .collect(Collectors.toSet());
        List<Product> products = productRepository.findAllByIdIn(productIds, pageable).getContent();
        log.info("OUT: {} products found", products.size());
        return productMapper.toProductDtos(products);
    }
}