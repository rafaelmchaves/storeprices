package com.store.infrastructure.input.controller.response;

import com.store.core.model.AmountType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Serdeable.Serializable
@Introspected
@Builder
@Getter
public class AttributeResponse {

    private AmountType amountType;

    private BigDecimal amount;

    private String[] colours;

}
