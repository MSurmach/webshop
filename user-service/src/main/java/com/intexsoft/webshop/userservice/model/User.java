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
    @Column(nullable = false, unique = true, length = 30)
    String login;
    @Column(nullable = false, length = 100)
    String firstname;
    @Column(nullable = false, length = 100)
    String lastname;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true, length = 100)
    String email;
    @Column(length = 50)
    String phoneNumber;
}
