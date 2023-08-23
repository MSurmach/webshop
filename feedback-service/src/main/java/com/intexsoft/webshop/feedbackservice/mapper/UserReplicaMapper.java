package com.intexsoft.webshop.feedbackservice.mapper;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCreatedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserReplicaMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UsersReplicaDto toUsersReplicaDto(UsersReplica usersReplica);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "login", source = "login")
    UsersReplica toUsersReplica(UserCreatedEvent userCreatedEvent);
}