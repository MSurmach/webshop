package com.intexsoft.webshop.orderservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long productId;
    @Column(nullable = false)
    BigDecimal productPrice;
    @Column(nullable = false)
    Short quantity;
    @Column(nullable = false)
    BigDecimal totalPrice;
    @ManyToOne
    Order order;
}