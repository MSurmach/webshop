package com.intexsoft.webshop.productservice.exception.notfound404;

import com.intexsoft.webshop.productservice.exception.Resource;

import static com.intexsoft.webshop.productservice.exception.Resource.ATTRIBUTE;

public class AttributeNotFoundException extends ResourceNotFoundException {
    public AttributeNotFoundException(Long attributeId) {
        super(ATTRIBUTE, attributeId);
    }
}
