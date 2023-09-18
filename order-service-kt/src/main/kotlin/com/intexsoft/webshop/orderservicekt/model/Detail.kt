package com.intexsoft.webshop.orderservicekt.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "detail")
class Detail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "product_id", nullable = false)
    var productId: Long,
    @Column(name = "product_price", nullable = false)
    var productPrice: BigDecimal,
    @Column(name = "quantity", nullable = false)
    var quantity: Short,
    @ManyToOne(fetch = FetchType.LAZY)
    var order: Order?,
)