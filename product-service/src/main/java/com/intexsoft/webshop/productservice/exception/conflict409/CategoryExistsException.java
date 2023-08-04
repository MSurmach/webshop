package com.intexsoft.webshop.productservice.exception.conflict409;

import static com.intexsoft.webshop.productservice.exception.Resource.CATEGORY;

public class CategoryExistsException extends SuchResourceExistsException {

    public CategoryExistsException(String categoryName) {
        super(CATEGORY, categoryName);
    }
}
