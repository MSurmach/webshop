package com.intexsoft.webshop.productservice.exception.notfound;

import com.intexsoft.webshop.productservice.exception.HttpStatusException;
import com.intexsoft.webshop.productservice.exception.Resource;
import org.springframework.http.HttpStatus;

public abstract class ResourceNotFoundException extends HttpStatusException {
    private static final String MSG_PATTERN = "%s with id = %d not found";

    protected ResourceNotFoundException(Resource resource, Long resourceId) {
        super(String.format(MSG_PATTERN, resource.name(), resourceId), HttpStatus.NOT_FOUND);
    }
}