package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class AttributeNotFoundException(attributeId: Long) : ResourceNotFoundException(Resource.ATTRIBUTE, attributeId)
