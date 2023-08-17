package com.intexsoft.webshop.feedbackservice.mapper;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCreatedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReplicaMapper {

    UsersReplica toUsersReplica(UsersReplicaDto usersReplicaDto);

    UsersReplicaDto toUsersReplicaDto(UsersReplica usersReplica);

    @Mapping(source = "userId", target = "id")
    UsersReplicaDto toUsersReplicaDto(UserCreatedEvent userCreatedEvent);

    @Mapping(source = "userId", target = "id")
    UsersReplica toUsersReplica(UserCreatedEvent userCreatedEvent);
}