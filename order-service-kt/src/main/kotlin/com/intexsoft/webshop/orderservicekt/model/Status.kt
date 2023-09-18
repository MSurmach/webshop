package com.intexsoft.webshop.orderservicekt.model

import com.intexsoft.webshop.orderservicekt.model.enums.StatusName
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SourceType
import java.time.LocalDateTime

@Entity
@Table(name = "status")
class Status(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null,
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    var statusName: StatusName = StatusName.INITIALIZED,
    @ManyToOne(fetch = FetchType.LAZY)
    var order: Order? = null
)