package com.intexsoft.webshop.productservice.exception.notfound404;

import com.intexsoft.webshop.productservice.exception.Resource;

public class SubcategoryNotFoundException extends ResourceNotFoundException {

    public SubcategoryNotFoundException(Long subcategoryId) {
        super(Resource.SUBCATEGORY, subcategoryId);
    }
}