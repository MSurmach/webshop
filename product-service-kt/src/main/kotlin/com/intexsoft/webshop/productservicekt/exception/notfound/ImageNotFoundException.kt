package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.Resource

class ImageNotFoundException(imageId: Long) : ResourceNotFoundException(Resource.IMAGE, imageId)