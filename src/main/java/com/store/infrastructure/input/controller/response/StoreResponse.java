package com.store.infrastructure.input.controller.response;

import com.store.core.model.StoreType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Serdeable.Serializable
@Introspected
@Builder
@Getter
public class StoreResponse {

    private String id;
    private String name;
    private String city;
    private StoreType type;

}
