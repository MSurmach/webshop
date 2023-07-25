package com.intexsoft.webshop.orderservice.model;

import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusName statusName;
    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
}