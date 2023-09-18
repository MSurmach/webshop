package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class SubcategoryNotFoundException(subcategoryId: Long) : ResourceNotFoundException(Resource.SUBCATEGORY, subcategoryId)