package com.intexsoft.webshop.userservice.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PasswordEncoderQualifier {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Named("encodePassword")
    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}