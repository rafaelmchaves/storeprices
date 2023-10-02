package com.store.core.usecases.impl;

import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.Store;
import com.store.core.usecases.StoreUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class StoreUseCaseImpl implements StoreUseCase {

    private final StoreDataProvider storeDataProvider;

    @Override
    public String save(Store store) {

        return this.storeDataProvider.save(store);

    }

    @Override
    public List<Store> query(String name) {
        return this.storeDataProvider.query(name);
    }
}
