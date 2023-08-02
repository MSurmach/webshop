package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.vendor.CreateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.UpdateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.model.Vendor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VendorMapper {
    Vendor toVendor(CreateVendorDto createVendorDto);

    VendorDto toVendorDto(Vendor vendor);

    Vendor toVendor(VendorDto vendorDto);

    List<VendorDto> toVendorDtos(List<Vendor> vendors);

    Vendor updateVendor(@MappingTarget Vendor vendor, UpdateVendorDto updateVendorDto);
}
