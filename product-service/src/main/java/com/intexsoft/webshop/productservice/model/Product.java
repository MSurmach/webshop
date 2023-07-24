package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @ManyToOne
    Subcategory subcategory;
    @ManyToOne
    Vendor vendor;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    Set<Image> images;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    Set<Feedback> feedbacks;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    Set<ProductShopLink> productShopLinks;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    Set<AttributeValue> attributeValues;
}