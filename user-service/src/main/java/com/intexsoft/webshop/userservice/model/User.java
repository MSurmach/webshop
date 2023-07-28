package com.intexsoft.webshop.userservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "login", nullable = false, unique = true, length = 30)
    String login;
    @Column(name = "firstname", nullable = false, length = 100)
    String firstname;
    @Column(name = "lastname", nullable = false, length = 100)
    String lastname;
    @Column(name = "password", nullable = false)
    String encodedPassword;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;
    @Column(name = "phone_number", length = 50)
    String phoneNumber;
}