package com.intexsoft.webshop.productservice.exception.notfound;

import com.intexsoft.webshop.productservice.exception.Resource;

public class AttributeNotFoundException extends ResourceNotFoundException {
    public AttributeNotFoundException(Long attributeId) {
        super(Resource.ATTRIBUTE, attributeId);
    }
}
