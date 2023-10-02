package com.store.infrastructure.output.mongo.mapper;

import com.store.core.model.Store;
import com.store.infrastructure.output.mongo.entity.StoreEmbedded;
import com.store.infrastructure.output.mongo.entity.StoreEntity;
import jakarta.inject.Singleton;

@Singleton
public class StoreMapper {

    public StoreEntity toStoreEntity(final Store store) {
        return StoreEntity.builder().name(store.getName()).city(store.getCity())
                .type(store.getType()).build();
    }

    public Store toStoreModel(final StoreEntity storeEntity) {
        return Store.builder().id(storeEntity.getId().toString())
                .name(storeEntity.getName())
                .city(storeEntity.getCity())
                .type(storeEntity.getType())
                .build();
    }

    public StoreEmbedded toStoreEmbedded(final Store store) {
        return StoreEmbedded.builder().id(store.getId()).name(store.getName()).city(store.getCity())
                .type(store.getType()).build();
    }
}
