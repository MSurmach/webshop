package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true, length = 100)
    String name;
    @Column(nullable = false, unique = true, length = 100)
    String email;
    @Column(length = 50)
    String phoneNumber;
    @CreationTimestamp
    @Column(nullable = false)
    Instant registeredAt;
    String about;
    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    Set<Feedback> feedbacks;
    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    Set<ShopProductLink> shopProductLinks = new HashSet<>();
}
