package com.intexsoft.webshop.productservice.dto.vendor;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateVendorDto {
    @Nullable
    @Size(max = 100)
    @Pattern(regexp = "^(?!\\s*$).+")
    String name;
    @Nullable
    @Pattern(regexp = "^(?!\\s*$).+")
    String about;
}
