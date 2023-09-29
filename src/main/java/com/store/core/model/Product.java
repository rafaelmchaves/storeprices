package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {

    private String id;
    private String name;
    private String date;
    private String brand;

    private Attribute attribute;

    private ProductType type;

}
