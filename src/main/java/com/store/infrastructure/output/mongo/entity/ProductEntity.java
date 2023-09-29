package com.store.infrastructure.output.mongo.entity;

import com.store.core.model.ProductType;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Builder
@MappedEntity(value = "product_entity")
public class ProductEntity {

    @Id
    @GeneratedValue
    @BsonId
    @BsonProperty(value = "_id")
    private ObjectId id;

    private String name;

    private String brand;

    private ProductType type;

    private AttributeEmbedded attributes;
}
