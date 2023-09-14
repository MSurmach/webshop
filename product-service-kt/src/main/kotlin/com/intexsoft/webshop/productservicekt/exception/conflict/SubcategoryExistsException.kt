package com.intexsoft.webshop.productservicekt.exception.conflict

import com.intexsoft.webshop.productservicekt.exception.Resource

class SubcategoryExistsException(subcategoryName: String) :
    SuchResourceExistsException(Resource.SUBCATEGORY, subcategoryName) 