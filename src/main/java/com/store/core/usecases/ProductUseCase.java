package com.store.core.usecases;

import com.store.core.model.Product;

import java.util.List;

public interface ProductUseCase {

    void save(Product product);

    List<Product> query(String name);
}
