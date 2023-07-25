package com.intexsoft.webshop.feedbackservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_feedback")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "header", nullable = false)
    String header;
    @Column(name = "score", nullable = false)
    Short score;
    @Column(name = "content", nullable = false)
    String content;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "product_id", nullable = false)
    Long productId;
    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "fk_product_feedback_user"))
    UsersReplica author;
}