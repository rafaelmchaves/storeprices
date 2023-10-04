package com.store.core.usecases.impl;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Product;
import com.store.core.model.ProductInflation;
import com.store.core.usecases.ProductUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Singleton
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductDataProvider productDataProvider;

    private final PriceDataProvider priceDataProvider;

    @Override
    public String save(Product product) {
       return productDataProvider.save(product);
    }

    @Override
    public List<Product> query(String name) {
        return productDataProvider.query(name);
    }

    @Override
    public ProductInflation calculateInflation(String id, LocalDate startDate, LocalDate endDate) {

        final var priceList = this.priceDataProvider.findAll(id, startDate, endDate);
        //TODO find all stored prices between startDate and endDate by id
        //TODO calculate the inflation

        return null;
    }
}
