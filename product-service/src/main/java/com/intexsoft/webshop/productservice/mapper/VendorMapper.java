package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.vendor.VendorCreateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorUpdateDto;
import com.intexsoft.webshop.productservice.model.Vendor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VendorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "products", ignore = true)
    Vendor toVendor(VendorCreateDto vendorCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    VendorDto toVendorDto(Vendor vendor);

    List<VendorDto> toVendorDtos(List<Vendor> vendors);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "products", ignore = true)
    Vendor updateVendor(@MappingTarget Vendor vendor, VendorUpdateDto vendorUpdateDto);
}