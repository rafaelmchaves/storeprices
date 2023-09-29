package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class ProductPrice {

    private Product product;

    private String price;

    private Store store;

    private LocalDate creationDate;

}
