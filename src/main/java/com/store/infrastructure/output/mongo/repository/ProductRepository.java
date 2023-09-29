package com.store.infrastructure.output.mongo.repository;

import com.store.infrastructure.output.mongo.entity.ProductEntity;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

@MongoRepository
public interface ProductRepository extends CrudRepository<ProductEntity, ObjectId> {

    ProductEntity findByNameLike(String name);
}
