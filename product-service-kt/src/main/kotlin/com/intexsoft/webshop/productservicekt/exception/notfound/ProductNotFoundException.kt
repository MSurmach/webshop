package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class ProductNotFoundException(productId: Long) : ResourceNotFoundException(Resource.PRODUCT, productId)
