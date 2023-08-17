package com.intexsoft.webshop.orderservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "detail")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "product_id", nullable = false)
    Long productId;
    @Column(name = "product_price", nullable = false)
    BigDecimal productPrice;
    @Column(name = "quantity", nullable = false)
    Short quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Order order;
}