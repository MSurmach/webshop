package com.intexsoft.webshop.feedbackservice.exception.notfound;

import com.intexsoft.webshop.feedbackservice.exception.Resource;

public class FeedbackNotFoundException extends ResourceNotFoundException {

    public FeedbackNotFoundException(Long feedbackId) {
        super(Resource.USER, feedbackId);
    }
}