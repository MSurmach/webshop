package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;
import static java.lang.Integer.MAX_VALUE;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        log.info("IN: request to create a new product received. Request body = {}", getAsString(productCreateDto));
        ProductDto createdProductDto = productService.createProduct(productCreateDto);
        log.info("OUT: new product created successfully. Response body = {}", getAsString(createdProductDto));
        return ResponseEntity.ok(createdProductDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findProducts(@PageableDefault(size = MAX_VALUE) Pageable pageable) {
        log.info("IN: request to find products received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<ProductDto> productDtos = productService.findProducts(pageable);
        log.info("OUT: response will return {} products", productDtos.size());
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable @Positive Long productId) {
        log.info("IN: request to find a product by id = {} received", productId);
        ProductDto productDto = productService.findProductById(productId);
        log.info("OUT: the category with id = {} found successfully. Response body = {}",
                productId, getAsString(productDto));
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable @Positive Long productId,
                                                    @RequestBody @Valid ProductUpdateDto productUpdateDto) {
        log.info("IN: request to update a product with id = {} received. Request body = {}",
                productId, getAsString(productUpdateDto));
        ProductDto updatedProductDto = productService.updateProduct(productId, productUpdateDto);
        log.info("OUT: the product with id = {} updated successfully. Response body = {}",
                productId, getAsString(updatedProductDto));
        return ResponseEntity.ok(updatedProductDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable @Positive Long productId) {
        log.info("IN: request to delete a product by id = {} received", productId);
        productService.deleteProductById(productId);
        log.info("OUT: the product with id = {} deleted successfully", productId);
        return ResponseEntity.noContent().build();
    }
}