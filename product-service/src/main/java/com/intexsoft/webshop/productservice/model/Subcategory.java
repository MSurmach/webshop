package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "subcategory")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false, unique = true)
    String name;
    @Column(name = "description", nullable = false)
    String description;
    @ManyToOne(fetch = FetchType.LAZY)
    Category category;
    @OneToMany(
            mappedBy = "subcategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<Attribute> attributes = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "subcategory",
            fetch = FetchType.LAZY
    )
    Set<Product> products = new LinkedHashSet<>();
}