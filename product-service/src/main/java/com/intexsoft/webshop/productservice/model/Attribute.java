package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "attribute")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "label", nullable = false, length = 50)
    String label;
    @ManyToOne(fetch = FetchType.LAZY)
    Subcategory subcategory;
    @OneToMany(
            mappedBy = "attribute",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<AttributeValue> attributeValues = new LinkedHashSet<>();
}