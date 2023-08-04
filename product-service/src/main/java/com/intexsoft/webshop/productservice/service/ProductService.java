package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductCreateDto productCreateDto);

    ProductDto findProductById(Long productId);

    List<ProductDto> findProducts(Pageable pageable);

    ProductDto updateProduct(Long productId, ProductUpdateDto productUpdateDto);

    void deleteProductById(Long productId);
}