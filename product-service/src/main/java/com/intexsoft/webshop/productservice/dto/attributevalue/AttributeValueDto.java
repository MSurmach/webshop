package com.intexsoft.webshop.productservice.dto.attributevalue;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValueDto {
    Long id;
    String value;
    AttributeDto attributeDto;
}