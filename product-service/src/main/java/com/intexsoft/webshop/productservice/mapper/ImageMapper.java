package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.image.ImageCreateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageDto;
import com.intexsoft.webshop.productservice.model.Image;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper {
    @Mapping(target = "product", ignore = true)
    Image ToImage(ImageCreateDto imageCreateDto);

    ImageDto toImageDto(Image image);

    List<ImageDto> toImageDtoList(Set<Image> set);
}
