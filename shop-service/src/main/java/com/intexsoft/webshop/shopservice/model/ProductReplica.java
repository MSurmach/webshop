package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReplica {
    @Id
    Long id;
    @Column(nullable = false)
    String name;
    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY
    )
    Set<ShopProductLink> shopProductLinks = new LinkedHashSet<>();
}
