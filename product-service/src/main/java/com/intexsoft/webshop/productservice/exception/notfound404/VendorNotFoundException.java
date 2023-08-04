package com.intexsoft.webshop.productservice.exception.notfound404;

import com.intexsoft.webshop.productservice.exception.Resource;

public class VendorNotFoundException extends ResourceNotFoundException {

    public VendorNotFoundException(Long vendorId) {
        super(Resource.VENDOR, vendorId);
    }
}
