package com.intexsoft.webshop.userservice.repository;

import com.intexsoft.webshop.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByLoginIgnoreCaseOrEmailIgnoreCase(String login, String email);
}