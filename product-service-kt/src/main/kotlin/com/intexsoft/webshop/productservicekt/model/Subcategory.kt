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
    val attributes: MutableSet<Attribute> = LinkedHashSet(),
    @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
    val products: MutableSet<Product> = LinkedHashSet()
) {
    fun addAttribute(attribute: Attribute) {
        attributes.add(attribute)
        attribute.subcategory = this
    }

    fun removeAttribute(attribute: Attribute) {
        attributes.remove(attribute)
        attribute.subcategory = null
    }
}