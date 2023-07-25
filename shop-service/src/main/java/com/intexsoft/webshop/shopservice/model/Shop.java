package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "shop")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false, unique = true, length = 100)
    String name;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;
    @Column(name = "phone_number", length = 50)
    String phoneNumber;
    @CreationTimestamp
    @Column(name = "registered_at", nullable = false)
    LocalDateTime registeredAt;
    @Column(name = "about")
    String about;
    @OneToMany(
            mappedBy = "shop",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    Set<Feedback> feedbacks = new LinkedHashSet<>();
    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY
    )
    Set<ShopProductLink> shopProductLinks = new LinkedHashSet<>();
}