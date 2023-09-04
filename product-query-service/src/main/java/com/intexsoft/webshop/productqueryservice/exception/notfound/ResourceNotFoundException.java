package com.intexsoft.webshop.productqueryservice.exception.notfound;

import com.intexsoft.webshop.productqueryservice.exception.HttpStatusException;
import com.intexsoft.webshop.productqueryservice.exception.Resource;
import org.springframework.http.HttpStatus;

public abstract class ResourceNotFoundException extends HttpStatusException {
    private static final String MSG_PATTERN = "%s with id = %d not found";

    protected ResourceNotFoundException(Resource resource, Long resourceId) {
        super(String.format(MSG_PATTERN, resource.name(), resourceId), HttpStatus.NOT_FOUND);
    }
}