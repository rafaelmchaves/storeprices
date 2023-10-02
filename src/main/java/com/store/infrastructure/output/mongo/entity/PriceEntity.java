package com.store.infrastructure.output.mongo.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@MappedEntity(value = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue
    @BsonId
    @BsonProperty(value = "_id")
    private ObjectId id;

    private LocalDateTime creationDate;

    private BigDecimal price;

    private ProductEmbedded product;

    private StoreEmbedded store;
}
