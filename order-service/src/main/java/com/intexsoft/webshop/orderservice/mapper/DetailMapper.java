package com.intexsoft.webshop.orderservice.mapper;

import com.intexsoft.webshop.orderservice.dto.detail.DetailCreateDto;
import com.intexsoft.webshop.orderservice.model.Detail;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DetailMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    Detail toDetail(DetailCreateDto detailCreateDto);

    @Named("calculateTotalPrice")
    default BigDecimal calculateTotalPrice(DetailCreateDto detailCreateDto) {
        BigDecimal productPrice = detailCreateDto.getProductPrice();
        Short quantity = detailCreateDto.getQuantity();
        return productPrice.multiply(BigDecimal.valueOf(quantity))
                .setScale(2);
    }
}