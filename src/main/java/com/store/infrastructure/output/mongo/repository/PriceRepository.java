package com.store.infrastructure.output.mongo.repository;


import com.store.core.model.ProductType;
import com.store.infrastructure.output.mongo.entity.PriceEntity;
import io.micronaut.data.mongodb.annotation.MongoFindQuery;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@MongoRepository
public interface PriceRepository extends CrudRepository<PriceEntity, ObjectId> {

    @MongoFindQuery(filter = "{ 'product.id': :productId, creationDate: { $gte: :startDate, $lte: :endDate} } ")
    List<PriceEntity> findAllByProductIdBetweenDates(String productId, LocalDate startDate, LocalDate endDate);

    @MongoFindQuery(filter = "{ 'product.type': :type, creationDate: { $gte: :startDate, $lte: :endDate} } ")
    List<PriceEntity> findAllByProductTypeBetweenDates(ProductType type, LocalDate startDate, LocalDate endDate);

}
