package com.intexsoft.webshop.orderservice.dto.status;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusDto {
    Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    StatusName statusName;
}
