package com.intexsoft.webshop.userservice.mapper;

import com.intexsoft.webshop.userservice.dto.UserApiExceptionDto;
import com.intexsoft.webshop.userservice.dto.UserDto;
import com.intexsoft.webshop.userservice.exception.SuchUserExistsException;
import com.intexsoft.webshop.userservice.model.User;
import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = PasswordEncoderQualifier.class)
public interface UserApiMapper {

    @Mapping(target = "encodedPassword", source = "plainPassword",
            qualifiedByName = "encodePassword")
    User toUser(UserDto userDto);

    @Mapping(target = "plainPassword", source = "encodedPassword", ignore = true)
    UserDto toUserDto(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserCreatedEvent toUserCreatedEvent(User user);

    default UserApiExceptionDto toUserApiExceptionDto(MethodArgumentNotValidException exception) {
        return UserApiExceptionDto.builder()
                .exceptionMessage(exception.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")))
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    default UserApiExceptionDto toUserApiExceptionDto(SuchUserExistsException exception) {
        return UserApiExceptionDto.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
    }
}