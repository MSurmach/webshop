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
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "shop_id", nullable = false)
    Long shopId;
    @Column(name = "user_id", nullable = false)
    Long userId;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;
    @Column(name = "payment_method", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    @Column(name = "comment")
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