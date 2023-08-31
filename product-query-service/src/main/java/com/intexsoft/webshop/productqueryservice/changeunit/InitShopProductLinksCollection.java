package com.intexsoft.webshop.productqueryservice.changeunit;

import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id = "2023-08-29-init-shop-product-links-collection", order = "001", author = "m.surmach")
@RequiredArgsConstructor
@Slf4j
public class InitShopProductLinksCollection {
    private final MongoTemplate mongoTemplate;
    @BeforeExecution
    public void beforeExecution() {
        log.info("Trying to create an empty collection for shopProductLinks");
        mongoTemplate.createCollection("shopProductLinks", CollectionOptions.empty()
                .validator(Validator.schema(MongoJsonSchema.builder()
                        .properties(
                                JsonSchemaProperty.int64("shopId"),
                                JsonSchemaProperty.int64("productId"),
                                JsonSchemaProperty.int32("quantity"),
                                JsonSchemaProperty.decimal128("price")
                        )
                        .build())));
    }

    @Execution
    public void changeSet() {
        // Nothing to do
    }

    @RollbackBeforeExecution
    public void rollbackBefore() {
        mongoTemplate.dropCollection("shopProductLinks");
    }

    @RollbackExecution
    public void rollback() {
        // Nothing to do
    }
}