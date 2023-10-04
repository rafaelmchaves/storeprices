package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class ProductInflation {

    private String id;
    private BigDecimal firstPrice;
    private BigDecimal lastPrice;
    private BigDecimal percentage;
    private LocalDate startDate;
    private LocalDate endDate;

}
