package com.intexsoft.webshop.orderservice.mapper;

import com.intexsoft.webshop.orderservice.dto.status.StatusDto;
import com.intexsoft.webshop.orderservice.model.Order;
import com.intexsoft.webshop.orderservice.model.Status;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatusMapper {
    StatusDto toStatusDto(Status status);

    Status toStatus(Order order, StatusName statusName);
}