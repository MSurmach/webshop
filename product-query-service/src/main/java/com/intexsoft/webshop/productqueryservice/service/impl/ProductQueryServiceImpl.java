package com.intexsoft.webshop.productqueryservice.service.impl;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.dto.ProductSearchDto;
import com.intexsoft.webshop.productqueryservice.exception.notfound404.ProductNotFoundException;
import com.intexsoft.webshop.productqueryservice.service.ProductCriteriaBuilder;
import com.intexsoft.webshop.productqueryservice.mapper.ProductEventMapper;
import com.intexsoft.webshop.productqueryservice.mapper.ProductMapper;
import com.intexsoft.webshop.productqueryservice.model.Product;
import com.intexsoft.webshop.productqueryservice.repository.ProductRepository;
import com.intexsoft.webshop.productqueryservice.service.ProductQueryService;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    private final ProductEventMapper productEventMapper;
    private final ProductMapper productMapper;
    private final ProductCriteriaBuilder productCriteriaBuilder;
    private final static String SHOP_PRODUCT_LINK_COLLECTION_NAME = "shopProductLinks";

    @Override
    public void saveProductFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        Product productFromEvent = productEventMapper.toProduct(productCreatedEvent);
        log.info("IN: trying to save new product = {} from {}",
                JsonUtils.getAsString(productFromEvent), productCreatedEvent.getClass().getSimpleName());
        Product savedProduct = productRepository.save(productFromEvent);
        log.info("OUT: product with id = {} saved successfully", savedProduct.getProductId());
    }

    @Override
    public List<ProductDto> findProducts(ProductSearchDto productSearchDto, Pageable pageable) {
        log.info("IN: trying to find products. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        Criteria criteria = productCriteriaBuilder.buildCriteria(productSearchDto.getFilterConditionDtos());
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from(SHOP_PRODUCT_LINK_COLLECTION_NAME)
                .localField("productId")
                .foreignField("productId")
                .as("productShopLink");
        MatchOperation matchOperation = Aggregation.match(criteria);
        SkipOperation skipOperation = Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize());
        LimitOperation limitOperation = Aggregation.limit(pageable.getPageSize());
        List<Product> products = productRepository.findAll(lookupOperation, matchOperation, skipOperation, limitOperation);
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
}