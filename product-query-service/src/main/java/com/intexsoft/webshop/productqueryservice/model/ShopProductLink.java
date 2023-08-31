package com.intexsoft.webshop.productqueryservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(collection = "shopProductLinks")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopProductLink {
    Long shopId;
    Long productId;
    Short quantity;
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal price;
}