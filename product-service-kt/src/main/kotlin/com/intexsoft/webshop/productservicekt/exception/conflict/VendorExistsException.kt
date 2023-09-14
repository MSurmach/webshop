package com.intexsoft.webshop.productservicekt.exception.conflict

import com.intexsoft.webshop.productservicekt.exception.Resource

class VendorExistsException(vendorName: String) : SuchResourceExistsException(Resource.VENDOR, vendorName)