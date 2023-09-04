package com.intexsoft.webshop.productqueryservice.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResourceRepository<T, ID> extends MongoRepository<T, ID> {
    List<T> findAll(AggregationOperation... aggregationOperations);
}
