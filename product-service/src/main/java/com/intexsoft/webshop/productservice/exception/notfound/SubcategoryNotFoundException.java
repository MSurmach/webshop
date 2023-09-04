package com.intexsoft.webshop.productservice.exception.notfound;

import com.intexsoft.webshop.productservice.exception.Resource;

public class SubcategoryNotFoundException extends ResourceNotFoundException {

    public SubcategoryNotFoundException(Long subcategoryId) {
        super(Resource.SUBCATEGORY, subcategoryId);
    }
}