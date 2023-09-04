package com.intexsoft.webshop.productqueryservice.exception.conflict;

import com.intexsoft.webshop.productqueryservice.exception.HttpStatusException;
import org.springframework.http.HttpStatus;

public class FilterOperationUnsupportedException extends HttpStatusException {
    private static final String MSG_PATTERN = "Filter operation value = \"%s\" is unsupported." +
            " List of supported values: %s";

    public FilterOperationUnsupportedException(String filterOperationValue,
                                               String supportedFilterOperationValues) {
        super(String.format(MSG_PATTERN, filterOperationValue, supportedFilterOperationValues), HttpStatus.CONFLICT);
    }
}
