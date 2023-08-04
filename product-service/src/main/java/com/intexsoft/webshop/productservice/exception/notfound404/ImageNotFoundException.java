package com.intexsoft.webshop.productservice.exception.notfound404;

import static com.intexsoft.webshop.productservice.exception.Resource.IMAGE;

public class ImageNotFoundException extends ResourceNotFoundException {
    public ImageNotFoundException(Long imageId) {
        super(IMAGE, imageId);
    }
}