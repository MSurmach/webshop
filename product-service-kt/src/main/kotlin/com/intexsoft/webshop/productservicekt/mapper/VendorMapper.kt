package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.vendor.VendorCreateDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorUpdateDto
import com.intexsoft.webshop.productservicekt.model.Vendor
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
)
interface VendorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "products", ignore = true)
    fun toVendor(vendorCreateDto: VendorCreateDto): Vendor

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    fun toVendorDto(vendor: Vendor): VendorDto
    fun toVendorDtos(vendors: List<Vendor>): List<VendorDto>

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "products", ignore = true)
    fun updateVendor(@MappingTarget vendor: Vendor, vendorUpdateDto: VendorUpdateDto): Vendor
}