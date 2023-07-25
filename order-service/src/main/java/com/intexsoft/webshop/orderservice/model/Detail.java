package com.intexsoft.webshop.orderservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(nullable = false)
    BigDecimal totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
}