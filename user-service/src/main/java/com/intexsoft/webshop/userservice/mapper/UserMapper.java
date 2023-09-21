package com.intexsoft.webshop.userservice.mapper;

import com.intexsoft.webshop.messagecommon.event.user.impl.UserCreatedEvent;
import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.model.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "encodedPassword", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    UserEntity toUser(UserCreateDto userCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    UserDto toUserDto(UserEntity userEntity);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "createdAt", ignore = true)
    UserCreatedEvent toUserCreatedEvent(UserEntity userEntity);
}