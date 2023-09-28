package com.store.infrastructure.input.controller.requests;

import com.store.core.model.AmountType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class AttributeRequest {

    private AmountType amountType;

    private BigDecimal amount;

    private String[] colours;

}
