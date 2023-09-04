package com.intexsoft.webshop.productqueryservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(collection = "shopProductLinks")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class ShopProductLink {
    Long productId;
    Long shopId;
    String shopName;
    Short quantity;
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal price;
}