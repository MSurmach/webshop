package com.intexsoft.webshop.productservicekt.exception.conflict

import com.intexsoft.webshop.productservicekt.exception.HttpStatusException
import com.intexsoft.webshop.productservicekt.exception.Resource
import org.springframework.http.HttpStatus

abstract class SuchResourceExistsException(resource: Resource, name: String) :
    HttpStatusException("Such ${resource.name} with name = $name already exists", HttpStatus.CONFLICT)