package com.intexsoft.webshop.productservice.exception.conflict409;

import com.intexsoft.webshop.productservice.exception.Resource;
import com.intexsoft.webshop.productservice.exception.HttpStatusException;
import org.springframework.http.HttpStatus;

public abstract class SuchResourceExistsException extends HttpStatusException {
    private static final String MSG_PATTERN = "Such %s with name = %s already exists";

    public SuchResourceExistsException(Resource resource, String name) {
        super(String.format(MSG_PATTERN, resource.name(), name), HttpStatus.CONFLICT);
    }
}