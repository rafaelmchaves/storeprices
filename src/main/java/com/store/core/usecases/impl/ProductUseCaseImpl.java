package com.store.core.usecases.impl;

import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Product;
import com.store.core.usecases.ProductUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductDataProvider productDataProvider;

    @Override
    public void save(Product product) {
        productDataProvider.save(product);
    }

    @Override
    public List<Product> query(String name) {
        return productDataProvider.query(name);
    }
}
