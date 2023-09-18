package com.intexsoft.webshop.productservicekt.model;

import jakarta.persistence.*

@Entity
@Table(name = "attribute")
class Attribute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "label", nullable = false, length = 50)
    var label: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var subcategory: Subcategory?,
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val attributeValues: MutableList<AttributeValue>?
)