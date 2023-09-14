package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class VendorNotFoundException(vendorId: Long) : ResourceNotFoundException(Resource.VENDOR, vendorId)
