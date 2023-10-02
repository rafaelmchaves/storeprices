package com.store.infrastructure.output.mongo.mapper;

import com.store.core.model.Store;
import com.store.infrastructure.output.mongo.entity.StoreEntity;
import jakarta.inject.Singleton;

@Singleton
public class StoreMapper {

    public StoreEntity toStoreEntity(Store store) {
        return StoreEntity.builder().name(store.getName()).city(store.getCity())
                .type(store.getType()).build();
    }
}
