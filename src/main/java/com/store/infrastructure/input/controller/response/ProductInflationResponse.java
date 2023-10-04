package com.store.infrastructure.input.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class ProductInflationResponse {

    private String id;
    private BigDecimal firstPrice;
    private BigDecimal lastPrice;
    private BigDecimal percentage;
    private LocalDate startDate;
    private LocalDate endDate;

}
