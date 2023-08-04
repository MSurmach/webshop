package com.intexsoft.webshop.productservice.exception.notfound404;

import static com.intexsoft.webshop.productservice.exception.Resource.VENDOR;

public class VendorNotFoundException extends ResourceNotFoundException {

    public VendorNotFoundException(Long vendorId) {
        super(VENDOR, vendorId);
    }
}
