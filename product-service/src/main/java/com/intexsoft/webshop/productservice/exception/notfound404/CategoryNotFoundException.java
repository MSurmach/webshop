package com.intexsoft.webshop.productservice.exception.notfound404;

import com.intexsoft.webshop.productservice.exception.Resource;

public class CategoryNotFoundException extends ResourceNotFoundException {

    public CategoryNotFoundException(Long categoryId) {
        super(Resource.CATEGORY, categoryId);
    }
}