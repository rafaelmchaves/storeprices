package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.Store;
import com.store.infrastructure.output.mongo.entity.StoreEntity;
import com.store.infrastructure.output.mongo.repository.StoreRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class StoreDataProviderImpl implements StoreDataProvider {

    private final StoreRepository repository;

    @Override
    public String save(Store store) {

        final var storeEntity = StoreEntity.builder().name(store.getName()).city(store.getCity()).type(store.getType()).build();
        return this.repository.save(storeEntity).getId().toString();

    }

    @Override
    public List<Store> query(String name) {
        final var result = this.repository.findByNameLike(name);
        return result.stream().map(
                storeEntity -> Store.builder().id(storeEntity.getId().toString())
                .name(storeEntity.getName())
                        .city(storeEntity.getCity())
                        .type(storeEntity.getType())
                        .build()
        ).toList();
    }

}
