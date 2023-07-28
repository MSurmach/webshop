package com.intexsoft.webshop.feedbackservice.mapper;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackApiMapper {

    UsersReplica toUsersReplica(UsersReplicaDto usersReplicaDto);

    UsersReplicaDto toUsersReplicaDto(UsersReplica usersReplica);

    UsersReplicaDto toUsersReplicaDto(UserCreatedEvent userCreatedEvent);
}