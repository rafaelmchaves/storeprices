package com.store.core.usecases.impl;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.Price;
import com.store.core.usecases.PriceUseCase;
import com.store.core.usecases.ProductUseCase;
import com.store.core.usecases.StoreUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Singleton
public class PriceUseCaseImpl implements PriceUseCase {

    private final ProductDataProvider productDataProvider;

    private final StoreDataProvider storeDataProvider;

    private final PriceDataProvider priceDataProvider;

    @Override
    public void add(Price price) {

        final var product = productDataProvider.findById(price.getProduct().getId());
        price.setProduct(product);

        //TODO find the store by id

        //TODO add new price in a list of price
    }
}
