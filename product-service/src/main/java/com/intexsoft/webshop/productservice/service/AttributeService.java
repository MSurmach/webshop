package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;

import java.util.List;

public interface AttributeService {
    List<AttributeDto> findAllByIdInAndSubcategoryId(List<Long> attributeIds, Long subcategoryId);
}
