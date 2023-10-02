package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.Store;
import com.store.infrastructure.output.mongo.mapper.StoreMapper;
import com.store.infrastructure.output.mongo.repository.StoreRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class StoreDataProviderImpl implements StoreDataProvider {

    private final StoreRepository repository;

    private final StoreMapper storeMapper;

    @Override
    public String save(Store store) {

        final var storeEntity = storeMapper.toStoreEntity(store);
        return this.repository.save(storeEntity).getId().toString();

    }

    @Override
    public List<Store> query(String name) {
        final var result = this.repository.findByNameLike(name);
        return result.stream().map(storeMapper::toStoreModel).toList();
    }

    @Override
    public Store findById(String id) {

        final var result = this.repository.findById(new ObjectId(id));
        return result.map(storeMapper::toStoreModel).orElse(null);

    }

}
