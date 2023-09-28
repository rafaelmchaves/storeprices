package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Attribute {

    private AmountType amountType;

    private BigDecimal amount;

    private String[] colours;

}
