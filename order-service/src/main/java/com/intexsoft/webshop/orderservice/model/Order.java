package com.intexsoft.webshop.orderservice.model;

import com.intexsoft.webshop.orderservice.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long shopId;
    @Column(nullable = false)
    Long userId;
    @CreationTimestamp(source = SourceType.DB)
    @Column(nullable = false)
    Instant createdAt;
    @Column(nullable = false)
    BigDecimal totalPrice;
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    String comment;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    Set<Detail> orderDetails;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    Set<Status> statuses = new LinkedHashSet<>();
}