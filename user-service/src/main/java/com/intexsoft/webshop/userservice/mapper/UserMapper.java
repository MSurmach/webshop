package com.intexsoft.webshop.userservice.mapper;

import com.intexsoft.webshop.userservice.dto.UserCreateDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.model.User;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;
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

    @Mapping(target = "encodedPassword", source = "plainPassword",
            qualifiedByName = "encodePassword")
    User toUser(UserCreateDto userCreateDto);

    UserDto toUserDto(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserCreatedEvent toUserCreatedEvent(User user);
}