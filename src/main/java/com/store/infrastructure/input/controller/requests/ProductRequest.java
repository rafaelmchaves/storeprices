package com.store.infrastructure.input.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductRequest {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String price;

    @JsonProperty(required = true)
    private StoreRequest store;

    private AttributeRequest attributeRequest;

}
