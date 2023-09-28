package com.store.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Store {

    private String id;
    private String name;
    private String city;
    private StoreType type;

}
