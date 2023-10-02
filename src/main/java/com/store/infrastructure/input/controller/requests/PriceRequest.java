package com.store.infrastructure.input.controller.requests;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Introspected
@Serdeable.Deserializable
public class PriceRequest {

    private String productId;

    private String storeId;

    private BigDecimal price;

}
