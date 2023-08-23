package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.image.ImageCreateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageDto;
import com.intexsoft.webshop.productservice.dto.image.ImageUpdateDto;
import com.intexsoft.webshop.productservice.model.Image;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImageMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "filePath", source = "filePath")
    @Mapping(target = "product", ignore = true)
    Image toImage(ImageCreateDto imageCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "filePath", source = "filePath")
    @Mapping(target = "product", ignore = true)
    Image toImage(ImageUpdateDto imageUpdateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "filePath", source = "filePath")
    ImageDto toImageDto(Image image);

    List<ImageDto> toImageDtoList(Set<Image> set);
}