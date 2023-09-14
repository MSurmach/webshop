package com.intexsoft.webshop.orderservicekt.mapper

import com.intexsoft.webshop.orderservicekt.dto.status.StatusDto
import com.intexsoft.webshop.orderservicekt.model.Order
import com.intexsoft.webshop.orderservicekt.model.Status
import com.intexsoft.webshop.orderservicekt.model.enums.StatusName
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface StatusMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "statusName", source = "statusName")
    fun toStatusDto(status: Status): StatusDto

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "statusName", source = "statusName")
    @Mapping(target = "order", source = "order")
    fun toStatus(order: Order, statusName: StatusName): Status
    fun toStatusDtoList(statuses: Set<Status>): List<StatusDto>
}