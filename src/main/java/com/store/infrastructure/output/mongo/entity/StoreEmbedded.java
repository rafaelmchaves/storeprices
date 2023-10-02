package com.store.infrastructure.output.mongo.entity;

import com.store.core.model.StoreType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Introspected
@Serdeable.Deserializable
@Serdeable.Serializable
public class StoreEmbedded {

    private String id;
    private String name;
    private String city;
    private StoreType type;

}
