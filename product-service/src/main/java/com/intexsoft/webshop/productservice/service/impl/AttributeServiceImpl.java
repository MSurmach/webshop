package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.mapper.AttributeMapper;
import com.intexsoft.webshop.productservice.model.Attribute;
import com.intexsoft.webshop.productservice.repository.AttributeRepository;
import com.intexsoft.webshop.productservice.service.AttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Override
    public List<AttributeDto> findAllByIdInAndSubcategoryId(List<Long> attributeIds, Long subcategoryId) {
        log.info("IN: trying to find all attributes with ids in {} belong to subcategory with id = {}",
                attributeIds, subcategoryId);
        List<Attribute> foundAttributes = attributeRepository.findAllByIdInAndSubcategoryId(attributeIds, subcategoryId);
        if (foundAttributes.size() < attributeIds.size()) {
            List<Long> foundAttributeIds = foundAttributes.stream().map(Attribute::getId).toList();
            attributeIds.removeAll(foundAttributeIds);
            throw new ResourceNotFoundException("Attributes with id(s) in " + attributeIds +
                    " don't belong to subcategory with id = " + subcategoryId);
        }
        log.info("OUT: {} attributes found in the subcategory with id = {}", foundAttributes.size(), subcategoryId);
        return attributeMapper.toAttributeDtos(foundAttributes);
    }
}