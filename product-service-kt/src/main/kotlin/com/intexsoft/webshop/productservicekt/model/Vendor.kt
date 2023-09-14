package com.intexsoft.webshop.productservicekt.model;

import jakarta.persistence.*

@Entity
@Table(name = "vendor")
class Vendor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false, unique = true)
    var name: String,
    @Column(name = "about")
    var about: String?,
    @OneToMany(
        mappedBy = "vendor",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    val products: MutableSet<Product> = LinkedHashSet()
) {
    fun addProduct(product: Product) {
        products.add(product)
        product.vendor = this
    }
}