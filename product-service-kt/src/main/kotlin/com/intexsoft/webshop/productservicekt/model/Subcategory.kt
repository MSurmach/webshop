package com.intexsoft.webshop.productservicekt.model;

import jakarta.persistence.*

@Entity
@Table(name = "subcategory")
class Subcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false, unique = true)
    var name: String,
    @Column(name = "description", nullable = false)
    var description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var category: Category,
    @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var attributes: MutableList<Attribute>?,
    @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
    var products: MutableList<Product>?
) {
    fun addAttribute(attribute: Attribute) {
        if (attributes == null) attributes = mutableListOf()
        attributes!!.add(attribute)
        attribute.subcategory = this
    }
}