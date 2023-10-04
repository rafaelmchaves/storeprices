package com.store.infrastructure.output.mongo.repository;


import com.store.infrastructure.output.mongo.entity.PriceEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.mongodb.annotation.MongoFindQuery;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@MongoRepository
public interface PriceRepository extends CrudRepository<PriceEntity, ObjectId> {

    @MongoFindQuery(filter = "{ 'product.id': :productId, creationDate: { $gte: :startDate, $lte: :endDate} } ")
    List<PriceEntity> findAllByProductId(String productId, LocalDate startDate, LocalDate endDate);

}
