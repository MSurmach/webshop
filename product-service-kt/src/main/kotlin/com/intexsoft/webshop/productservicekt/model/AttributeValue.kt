package com.intexsoft.webshop.productservicekt.model;

import jakarta.persistence.*

@Entity
@Table(name = "attribute_value")
class AttributeValue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "value", nullable = false, length = 50)
    var value: String,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    var attribute: Attribute?,
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product? = null
)