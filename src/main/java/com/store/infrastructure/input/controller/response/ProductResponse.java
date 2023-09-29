package com.store.infrastructure.input.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.core.model.ProductType;
import com.store.infrastructure.input.controller.requests.AttributeRequest;
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
@Serdeable.Serializable
public class ProductResponse implements Serializable {

    private String id;

    private String name;

    private String brand;

    private AttributeResponse attributes;

    private ProductType type;

}
