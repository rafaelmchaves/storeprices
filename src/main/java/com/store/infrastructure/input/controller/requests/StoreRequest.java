package com.store.infrastructure.input.controller.requests;

import com.store.core.model.StoreType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Serdeable.Deserializable
@Introspected
@Builder
@Getter
public class StoreRequest {

    private String id;
    private String name;
    private String city;
    private StoreType type;

}
