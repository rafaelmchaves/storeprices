package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private Store store;
    private String date;
    private String brand;

    private Attribute attribute;
}
