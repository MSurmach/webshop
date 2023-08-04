package com.intexsoft.webshop.productservice.exception.notfound404;

import static com.intexsoft.webshop.productservice.exception.Resource.PRODUCT;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long productId) {
        super(PRODUCT, productId);
    }
}
