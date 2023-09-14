package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.product.ProductCreateDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductUpdateDto
import com.intexsoft.webshop.productservicekt.model.AttributeValue
import com.intexsoft.webshop.productservicekt.model.Product
import com.intexsoft.webshop.productservicekt.model.Subcategory
import com.intexsoft.webshop.productservicekt.model.Vendor
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    uses = [SubcategoryMapper::class, VendorMapper::class, ImageMapper::class, AttributeValueMapper::class]
)
interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productCreateDto.name")
    @Mapping(target = "orderQuantity", ignore = true)
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "images", source = "productCreateDto.imageCreateDtos")
    @Mapping(target = "attributeValues", source = "attributeValues")
    fun toProduct(
        productCreateDto: ProductCreateDto, vendor: Vendor,
        subcategory: Subcategory, attributeValues: List<AttributeValue>
    ): Product

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "subcategoryDto", source = "subcategory")
    @Mapping(target = "vendorDto", source = "vendor")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "attributeValueDtos", source = "attributeValues")
    fun toProductDto(product: Product): ProductDto
    fun toProductDtos(productList: List<Product?>): List<ProductDto>

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productUpdateDto.name")
    @Mapping(target = "orderQuantity", ignore = true)
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    fun updateProduct(
        @MappingTarget product: Product, productUpdateDto: ProductUpdateDto, vendor: Vendor?,
        subcategory: Subcategory?
    ): Product
}