package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class Price {

    private Product product;

    private BigDecimal price;

    private Store store;

    private LocalDate creationDate;

}
