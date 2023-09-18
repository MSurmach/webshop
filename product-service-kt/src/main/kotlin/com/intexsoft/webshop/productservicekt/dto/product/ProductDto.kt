package com.intexsoft.webshop.productservicekt.dto.product

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorDto

data class ProductDto(
    val id: Long,
    val name: String,
    val orderQuantity: Short = 0,
    @JsonProperty("subcategory")
    val subcategoryDto: SubcategoryDto?,
    @JsonProperty("vendor")
    val vendorDto: VendorDto?,
    @JsonProperty("images")
    val imageDtos: List<ImageDto>?,
    @JsonProperty("attributeValues")
    val attributeValueDtos: List<AttributeValueDto>?
)