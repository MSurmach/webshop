package com.intexsoft.webshop.productqueryservice.exception.notfound;

import com.intexsoft.webshop.productqueryservice.exception.Resource;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long productId) {
        super(Resource.PRODUCT, productId);
    }
}