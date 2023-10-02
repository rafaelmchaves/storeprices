package com.store.infrastructure.output.mongo.repository;

import com.store.infrastructure.output.mongo.entity.ProductEntity;
import com.store.infrastructure.output.mongo.entity.StoreEntity;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.util.List;

@MongoRepository
public interface StoreRepository extends CrudRepository<StoreEntity, ObjectId> {

    List<StoreEntity> findByNameLike(String name);
}
