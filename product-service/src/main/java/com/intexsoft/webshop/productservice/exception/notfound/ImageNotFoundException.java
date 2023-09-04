package com.intexsoft.webshop.productservice.exception.notfound;

import com.intexsoft.webshop.productservice.exception.Resource;

public class ImageNotFoundException extends ResourceNotFoundException {
    public ImageNotFoundException(Long imageId) {
        super(Resource.IMAGE, imageId);
    }
}