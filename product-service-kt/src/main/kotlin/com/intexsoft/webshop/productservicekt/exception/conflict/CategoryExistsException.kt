package com.intexsoft.webshop.productservicekt.exception.conflict

import com.intexsoft.webshop.productservicekt.exception.Resource

class CategoryExistsException(categoryName: String) : SuchResourceExistsException(Resource.CATEGORY, categoryName)
