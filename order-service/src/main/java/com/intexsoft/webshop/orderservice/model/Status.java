package com.intexsoft.webshop.orderservice.model;

import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    StatusName statusName;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Order order;

    public Status(StatusName statusName) {
        this.statusName = statusName;
    }
}