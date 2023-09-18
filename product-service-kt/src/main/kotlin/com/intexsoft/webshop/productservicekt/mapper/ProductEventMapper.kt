package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent
import com.intexsoft.webshop.productservicekt.model.AttributeValue
import com.intexsoft.webshop.productservicekt.model.Product
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface ProductEventMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "images", source = "images")
    @Mapping(target = "attributeValues", source = "attributeValues")
    @Mapping(target = "createdAt", ignore = true)
    fun toProductEventCreated(product: Product): ProductCreatedEvent

    @Mapping(target = "id", source = "id")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "attributeId", source = "attribute.id")
    fun toAttributeValue(attributeValue: AttributeValue): ProductCreatedEvent.AttributeValue

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "createdAt", ignore = true)
    fun toProductEventDeleted(productId: Long?): ProductDeletedEvent

    @Mapping(target = "name", source = "name")
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "createdAt", ignore = true)
    fun toProductEventUpdated(product: Product): ProductUpdatedEvent

    @Mapping(target = "result", source = "result")
    @Mapping(target = "productIds", source = "productIds")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "createdAt", ignore = true)
    fun toProductOrderQuantityIncrementedEvent(
        productIds: Set<Long>,
        result: Boolean,
        orderId: Long
    ): ProductOrderQuantityIncrementedEvent
}