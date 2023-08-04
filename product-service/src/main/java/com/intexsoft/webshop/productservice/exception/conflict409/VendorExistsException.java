package com.intexsoft.webshop.productservice.exception.conflict409;

import static com.intexsoft.webshop.productservice.exception.Resource.VENDOR;

public class VendorExistsException extends SuchResourceExistsException {
    public VendorExistsException(String vendorName) {
        super(VENDOR, vendorName);
    }
}