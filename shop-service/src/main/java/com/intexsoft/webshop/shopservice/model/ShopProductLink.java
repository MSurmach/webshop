package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "shop_product_link")
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
    @Column(name = "price", nullable = false)
    BigDecimal price;
}