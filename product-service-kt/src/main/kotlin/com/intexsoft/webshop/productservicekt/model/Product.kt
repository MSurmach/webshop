package com.intexsoft.webshop.productservicekt.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "orderQuantity")
    var orderQuantity: Short = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    var subcategory: Subcategory,
    @ManyToOne(fetch = FetchType.LAZY)
    var vendor: Vendor,
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: MutableList<Image>?,
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var attributeValues: MutableList<AttributeValue>?
) {
    fun addImage(image: Image) {
        if (images == null) images = mutableListOf()
        images!!.add(image)
        image.product = this
    }

    fun removeImage(image: Image) {
        images?.remove(image)
        image.product = null
    }

    fun addAttributeValue(attributeValue: AttributeValue) {
        if (attributeValues == null) attributeValues = mutableListOf()
        attributeValues!!.add(attributeValue)
        attributeValue.product = this
    }

    fun removeAttributeValue(attributeValue: AttributeValue) {
        attributeValues?.remove(attributeValue)
        attributeValue.product = null
    }
}