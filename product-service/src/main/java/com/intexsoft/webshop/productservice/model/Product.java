package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    Subcategory subcategory;
    @ManyToOne(fetch = FetchType.LAZY)
    Vendor vendor;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<Image> images = new LinkedHashSet<>();
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<Feedback> feedbacks = new LinkedHashSet<>();
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    Set<ProductShopLink> productShopLinks;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<AttributeValue> attributeValues = new LinkedHashSet<>();
}