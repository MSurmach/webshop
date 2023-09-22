package com.intexsoft.webshop.userservice.repository;

import com.intexsoft.webshop.userservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findUserByLoginIgnoreCaseOrEmailIgnoreCase(String login, String email);

    Optional<UserEntity> findUserByLoginIgnoreCase(String login);
}