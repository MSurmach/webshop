package com.intexsoft.webshop.feedbackservice.service;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;

public interface UsersReplicaService {
    UsersReplicaDto createUsersReplica(UserCreatedEvent userCreatedEvent);
}
