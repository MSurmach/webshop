package com.intexsoft.webshop.productservicekt.exception.notfound

import com.intexsoft.webshop.productservicekt.exception.HttpStatusException
import com.intexsoft.webshop.productservicekt.exception.Resource
import org.springframework.http.HttpStatus

abstract class ResourceNotFoundException(resource: Resource, resourceId: Long) :
    HttpStatusException("${resource.name} with id = $resourceId not found", HttpStatus.NOT_FOUND)