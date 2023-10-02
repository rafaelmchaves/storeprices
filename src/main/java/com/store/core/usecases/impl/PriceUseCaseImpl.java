package com.store.core.usecases.impl;

import com.store.core.model.Price;
import com.store.core.usecases.PriceUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Singleton
public class PriceUseCaseImpl implements PriceUseCase {

    @Override
    public void add(Price price) {

        //TODO find the product by id

        //TODO find the store by id

        //TODO add new price in a list of price
    }
}
