package com.intexsoft.webshop.userservice.mapper;

import com.intexsoft.webshop.messagecommon.event.user.impl.UserCreatedEvent;
import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Named("encodePassword")
    default String encodePassword(String plainPassword) {
        return ENCODER.encode(plainPassword);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "encodedPassword", source = "plainPassword", qualifiedByName = "encodePassword")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    User toUser(UserCreateDto userCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    UserDto toUserDto(User user);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "createdAt", ignore = true)
    UserCreatedEvent toUserCreatedEvent(User user);
}