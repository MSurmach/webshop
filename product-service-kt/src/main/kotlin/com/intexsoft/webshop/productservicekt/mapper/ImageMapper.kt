package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.image.ImageCreateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageUpdateDto
import com.intexsoft.webshop.productservicekt.model.Image
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface ImageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", source = "filePath")
    @Mapping(target = "product", ignore = true)
    fun toImage(imageCreateDto: ImageCreateDto): Image

    @Mapping(target = "id", source = "id")
    @Mapping(target = "filePath", source = "filePath")
    @Mapping(target = "product", ignore = true)
    fun toImage(imageUpdateDto: ImageUpdateDto): Image

    @Mapping(target = "id", source = "id")
    @Mapping(target = "filePath", source = "filePath")
    fun toImageDto(image: Image): ImageDto
    fun toImageDtoList(set: MutableSet<Image>): MutableList<ImageDto>
}