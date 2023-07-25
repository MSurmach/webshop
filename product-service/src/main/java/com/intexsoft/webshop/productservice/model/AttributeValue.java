package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "attribute_value")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "value", nullable = false, length = 50)
    String value;
    @OneToOne
    Attribute attribute;
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;
}