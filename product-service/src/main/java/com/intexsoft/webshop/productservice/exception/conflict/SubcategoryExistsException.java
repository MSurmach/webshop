package com.intexsoft.webshop.productservice.exception.conflict;

import com.intexsoft.webshop.productservice.exception.Resource;

public class SubcategoryExistsException extends SuchResourceExistsException {
    public SubcategoryExistsException(String subcategoryName) {
        super(Resource.SUBCATEGORY, subcategoryName);
    }
}