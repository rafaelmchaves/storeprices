package com.store.core.usecases;

import com.store.core.model.Product;

public interface ProductUseCase {

    void save(Product product);

    Product query(String name);
}
