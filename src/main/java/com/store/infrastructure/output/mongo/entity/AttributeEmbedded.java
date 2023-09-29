package com.store.infrastructure.output.mongo.entity;

import com.store.core.model.AmountType;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@Introspected
@Serdeable.Deserializable
@Serdeable.Serializable
public class AttributeEmbedded {

    private AmountType amountType;

    private BigDecimal amount;

    private String[] colours;
}
