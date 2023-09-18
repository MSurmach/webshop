package com.intexsoft.webshop.productservicekt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "filePath", nullable = false, length = 512, unique = true)
    var filePath: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product?
)