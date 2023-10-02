package com.store.core.usecases;

import com.store.core.model.Store;

import java.util.List;

public interface StoreUseCase {

    String save(Store store);

    List<Store> query(String name);
}
