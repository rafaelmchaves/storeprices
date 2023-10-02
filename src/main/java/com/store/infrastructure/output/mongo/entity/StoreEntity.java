package com.store.infrastructure.output.mongo.entity;

import com.store.core.model.StoreType;
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
@MappedEntity(value = "stores")
public class StoreEntity {

    @Id
    @GeneratedValue
    @BsonId
    @BsonProperty(value = "_id")
    private ObjectId id;
    private String name;
    private String city;
    private StoreType type;

}
