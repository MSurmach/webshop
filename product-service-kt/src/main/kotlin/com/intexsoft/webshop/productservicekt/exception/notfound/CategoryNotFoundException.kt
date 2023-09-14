package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class CategoryNotFoundException(categoryId: Long) : ResourceNotFoundException(Resource.CATEGORY, categoryId)