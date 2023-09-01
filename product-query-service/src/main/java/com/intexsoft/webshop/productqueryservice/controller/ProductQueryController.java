package com.intexsoft.webshop.productqueryservice.controller;

import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.dto.ProductSearchDto;
import com.intexsoft.webshop.productqueryservice.service.ProductQueryService;
import com.intexsoft.webshop.productqueryservice.util.JsonUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductQueryController {
    private final ProductQueryService productQueryService;

    @PostMapping("/search")
    public ResponseEntity<List<ProductDto>> findProducts(@PageableDefault(size = MAX_VALUE) Pageable pageable,
                                                         @Valid @RequestBody ProductSearchDto productSearchDto) {
        log.info("IN: request to find products received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<ProductDto> productDtos = productQueryService.findProducts(productSearchDto, pageable);
        log.info("OUT: response will return {} products", productDtos.size());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable @Positive Long productId) {
        log.info("IN: request to find a product by id = {} received", productId);
        ProductDto productDto = productQueryService.findProductById(productId);
        log.info("OUT: the category with id = {} found successfully. Response body = {}",
                productId, JsonUtils.getAsString(productDto));
        return ResponseEntity.ok(productDto);
    }
}