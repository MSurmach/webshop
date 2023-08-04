package com.intexsoft.webshop.productservice.exception.conflict409;

import com.intexsoft.webshop.productservice.exception.Resource;

public class CategoryExistsException extends SuchResourceExistsException {

    public CategoryExistsException(String categoryName) {
        super(Resource.CATEGORY, categoryName);
    }
}
