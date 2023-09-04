package com.intexsoft.webshop.productqueryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productqueryservice.validator.ValidSearchFilter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchDto {
    @Valid
    @ValidSearchFilter
    @JsonProperty("filters")
    List<FilterConditionDto> filterConditionDtos;
}
