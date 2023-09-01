package com.intexsoft.webshop.productqueryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productqueryservice.service.FilterOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterConditionDto {
    @NotBlank
    String fieldName;
    @JsonProperty("operation")
    FilterOperation filterOperation;
    @NotNull
    Object fieldValue;
}