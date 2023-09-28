package com.store.infrastructure.input.controller.requests;

import com.store.core.model.StoreType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoreRequest {

    private String id;
    private String name;
    private String city;
    private StoreType type;

}
