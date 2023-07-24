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
public class ShopReplica {
    @Id
    Long id;
    @Column(nullable = false, unique = true, length = 100)
    String name;

    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY
    )
    Set<ProductShopLink> productShopLinks;
}