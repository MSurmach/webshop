package com.intexsoft.webshop.feedbackservice.exception.notfound;

import com.intexsoft.webshop.feedbackservice.exception.Resource;

public class UserReplicaNotFoundException extends ResourceNotFoundException {

    public UserReplicaNotFoundException(Long userId) {
        super(Resource.USER, userId);
    }
}