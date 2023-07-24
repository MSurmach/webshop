package com.intexsoft.webshop.productservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String header;
    @Column(nullable = false)
    Short score;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    Long authorId;
    @CreationTimestamp
    @Column(nullable = false)
    Instant createdAt;
    @ManyToOne
    Product product;
}