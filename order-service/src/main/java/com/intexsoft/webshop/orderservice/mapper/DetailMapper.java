package com.intexsoft.webshop.orderservice.mapper;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderservice.dto.detail.DetailCreateDto;
import com.intexsoft.webshop.orderservice.dto.detail.DetailDto;
import com.intexsoft.webshop.orderservice.model.Detail;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
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
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    DetailDto toDetailDto(Detail detail);

    List<DetailDto> toDetailDtoList(Set<Detail> details);

    List<OrderInitializedEvent.InitOrderProductDetail> toInitOrderProductDetailList(Set<Detail> details);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    OrderInitializedEvent.InitOrderProductDetail mapToInitOrderProductDetail(Detail detail);
}