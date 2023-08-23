package com.intexsoft.webshop.orderservice.mapper;

import com.intexsoft.webshop.orderservice.dto.status.StatusDto;
import com.intexsoft.webshop.orderservice.model.Order;
import com.intexsoft.webshop.orderservice.model.Status;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatusMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "statusName", source = "statusName")
    StatusDto toStatusDto(Status status);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "statusName", source = "statusName")
    @Mapping(target = "order", source = "order")
    Status toStatus(Order order, StatusName statusName);
}