package com.store.infrastructure.output.mongo.entity;

import com.store.core.model.ProductType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Introspected
@Serdeable.Deserializable
@Serdeable.Serializable
@Embeddable
public class ProductEmbedded {

    @EmbeddedId
    private String id;

    private String name;

    private String brand;

    private ProductType type;

    private AttributeEmbedded attributes;
}
