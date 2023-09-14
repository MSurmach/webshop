package com.intexsoft.webshop.orderservicekt.model

import com.intexsoft.webshop.orderservicekt.model.enums.PaymentMethod
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SourceType
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "\"order\"")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "pickup_point_id", nullable = false)
    var pickupPointId: Long,
    @Column(name = "shop_id", nullable = false)
    var shopId: Long,
    @Column(name = "user_id", nullable = false)
    var userId: Long,
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime,
    @Column(name = "total_price", nullable = false)
    var totalPrice: BigDecimal,
    @Column(name = "payment_method", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    var paymentMethod: PaymentMethod,
    @Column(name = "comment")
    var comment: String?,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var orderDetails: MutableSet<Detail> = LinkedHashSet(),
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var statuses: MutableSet<Status> = LinkedHashSet()

) {
    fun addDetail(detail: Detail) {
        orderDetails.add(detail)
        detail.order = this
    }

    fun removeDetail(detail: Detail) {
        orderDetails.remove(detail)
        detail.order = null
    }

    fun addStatus(status: Status) {
        statuses.add(status)
        status.order = this
    }

    fun removeStatus(status: Status) {
        statuses.remove(status)
        status.order = null
    }
}