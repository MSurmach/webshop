package com.intexsoft.webshop.productservice.exception.notfound404;

import static com.intexsoft.webshop.productservice.exception.Resource.CATEGORY;

public class CategoryNotFoundException extends ResourceNotFoundException {

    public CategoryNotFoundException(Long categoryId) {
        super(CATEGORY, categoryId);
    }
}