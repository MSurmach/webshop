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
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Image> images = new LinkedHashSet<>();
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<AttributeValue> attributeValues = new LinkedHashSet<>();

    public void addImage(Image image) {
        images.add(image);
        image.setProduct(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
    }

    public void addAttributeValue(AttributeValue attributeValue) {
        attributeValues.add(attributeValue);
        attributeValue.setProduct(this);
    }

    public void removeAttributeValue(AttributeValue attributeValue) {
        attributeValues.remove(attributeValue);
        attributeValue.setProduct(null);
    }
}