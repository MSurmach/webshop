package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopProductLink {
    @Id
    @ManyToOne
    Shop shop;
    @Id
    @ManyToOne
    ProductReplica product;
    @Column(nullable = false)
    BigDecimal price;
}
