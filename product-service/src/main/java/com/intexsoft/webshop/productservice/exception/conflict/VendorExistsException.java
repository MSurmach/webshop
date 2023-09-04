package com.intexsoft.webshop.productservice.exception.conflict;

import com.intexsoft.webshop.productservice.exception.Resource;

public class VendorExistsException extends SuchResourceExistsException {
    public VendorExistsException(String vendorName) {
        super(Resource.VENDOR, vendorName);
    }
}