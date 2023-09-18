package com.intexsoft.webshop.orderservicekt.mapper

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent
import com.intexsoft.webshop.orderservicekt.dto.detail.DetailCreateDto
import com.intexsoft.webshop.orderservicekt.dto.detail.DetailDto
import com.intexsoft.webshop.orderservicekt.model.Detail
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
)
interface DetailMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    fun toDetail(detailCreateDto: DetailCreateDto): Detail

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    fun toDetailDto(detail: Detail): DetailDto
    fun toDetailDtoList(details: Set<Detail>): List<DetailDto>
    fun toInitOrderProductDetailList(details: Set<Detail>): List<OrderInitializedEvent.InitOrderProductDetail>

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    fun mapToInitOrderProductDetail(detail: Detail): OrderInitializedEvent.InitOrderProductDetail
}