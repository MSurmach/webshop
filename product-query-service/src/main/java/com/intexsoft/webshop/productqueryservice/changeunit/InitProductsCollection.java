package com.intexsoft.webshop.productqueryservice.changeunit;

import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id = "2023-08-29-init-products-collection", order = "002", author = "m.surmach")
@RequiredArgsConstructor
@Slf4j
public class InitProductsCollection {
    private final MongoTemplate mongoTemplate;

    @BeforeExecution
    public void beforeExecution() {
        mongoTemplate.createCollection("products", CollectionOptions.empty()
                .validator(Validator.schema(MongoJsonSchema.builder()
                        .properties(
                                JsonSchemaProperty.int64("productId"),
                                JsonSchemaProperty.string("productName"),
                                JsonSchemaProperty.object("vendor")
                                        .properties(
                                                JsonSchemaProperty.int64("vendorId"),
                                                JsonSchemaProperty.string("vendorName"),
                                                JsonSchemaProperty.string("about")
                                        ),
                                JsonSchemaProperty.object("subcategory")
                                        .properties(
                                                JsonSchemaProperty.int64("subcategoryId"),
                                                JsonSchemaProperty.string("subcategoryName"),
                                                JsonSchemaProperty.string("description"),
                                                JsonSchemaProperty.object("category")
                                                        .properties(
                                                                JsonSchemaProperty.int64("categoryId"),
                                                                JsonSchemaProperty.string("categoryName")
                                                        ),
                                                JsonSchemaProperty.array("attributes")
                                        ),
                                JsonSchemaProperty.array("images"),
                                JsonSchemaProperty.array("attributeValues")
                        ).build())));
    }

    @Execution
    public void changeSet() {
        // Nothing to do
    }

    @RollbackBeforeExecution
    public void rollbackBefore() {
        mongoTemplate.dropCollection("products");
    }

    @RollbackExecution
    public void rollback() {
        // Nothing to do
    }
}