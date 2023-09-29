package com.store.infrastructure.input.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Introspected
@Serdeable.Deserializable
public class ProductRequest implements Serializable {

    @JsonProperty(required = true)
    private String name;

    private String brand;

    private AttributeRequest attributeRequest;

}
