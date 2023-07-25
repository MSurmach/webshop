package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product_shop_link")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductShopLink {
    @Id
    @ManyToOne
    Product product;
    @Id
    @ManyToOne
    ShopReplica shop;
}