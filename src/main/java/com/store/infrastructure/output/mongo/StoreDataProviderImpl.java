package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.Store;
import com.store.infrastructure.output.mongo.entity.StoreEntity;
import com.store.infrastructure.output.mongo.repository.StoreRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Singleton
public class StoreDataProviderImpl implements StoreDataProvider {

    private final StoreRepository repository;

    @Override
    public String save(Store store) {

        final var storeEntity = StoreEntity.builder().name(store.getName()).city(store.getCity()).type(store.getType()).build();
        return this.repository.save(storeEntity).getId().toString();

    }

}
