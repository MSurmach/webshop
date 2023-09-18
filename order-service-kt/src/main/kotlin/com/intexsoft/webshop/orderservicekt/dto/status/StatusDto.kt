package com.intexsoft.webshop.orderservicekt.dto.status

import com.fasterxml.jackson.annotation.JsonFormat
import com.intexsoft.webshop.orderservicekt.model.enums.StatusName
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDateTime

data class StatusDto(
    var id: Long,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    @Enumerated(EnumType.STRING)
    val statusName: StatusName,
)
