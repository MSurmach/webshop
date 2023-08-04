package com.intexsoft.webshop.productservice.exception.notfound404;

import static com.intexsoft.webshop.productservice.exception.Resource.SUBCATEGORY;

public class SubcategoryNotFoundException extends ResourceNotFoundException {

    public SubcategoryNotFoundException(Long subcategoryId) {
        super(SUBCATEGORY, subcategoryId);
    }
}