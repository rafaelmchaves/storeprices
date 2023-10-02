package com.store.core.dataprovider;

import com.store.core.model.Store;

import java.util.List;

public interface StoreDataProvider {

    String save(Store store);

    List<Store> query(String name);

    Store findById(String id);
}
