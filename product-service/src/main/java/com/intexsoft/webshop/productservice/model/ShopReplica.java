package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "shop_replica")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopReplica {
    @Id
    Long id;
    @Column(name = "name", nullable = false, unique = true, length = 100)
    String name;

    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    Set<ProductShopLink> productShopLinks = new LinkedHashSet<>();
}