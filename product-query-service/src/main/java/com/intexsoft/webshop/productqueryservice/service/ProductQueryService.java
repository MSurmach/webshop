package com.intexsoft.webshop.productqueryservice.service;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.dto.ProductSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryService {
    void saveProductFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent);

    List<ProductDto> findProducts(ProductSearchDto productSearchDto, Pageable pageable);

    ProductDto findProductById(Long productId);
}