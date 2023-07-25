package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "header", nullable = false)
    String header;
    @Column(name = "score", nullable = false)
    Short score;
    @Column(name = "content", nullable = false)
    String content;
    @Column(name = "author_id", nullable = false)
    Long authorId;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    Shop shop;
}