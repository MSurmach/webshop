package com.intexsoft.webshop.productqueryservice.repository.impl;

import com.intexsoft.webshop.productqueryservice.repository.ResourceRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class ResourceRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements ResourceRepository<T, ID> {
    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public ResourceRepositoryImpl(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
        this.entityInformation = entityInformation;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<T> findAll(AggregationOperation... aggregationOperations) {
        return mongoOperations
                .aggregate(Aggregation.newAggregation(aggregationOperations),
                        entityInformation.getCollectionName(),
                        entityInformation.getJavaType())
                .getMappedResults();
    }
}
