package com.store.core.usecases.impl;

import com.store.core.activities.CalculateInflationActivity;
import com.store.core.activities.GetMonthAverageByStore;
import com.store.core.dataprovider.StoreDataProvider;
import com.store.core.model.ProductInflation;
import com.store.core.model.Store;
import com.store.core.usecases.StoreUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Singleton
public class StoreUseCaseImpl implements StoreUseCase {

    private final StoreDataProvider storeDataProvider;

    private final GetMonthAverageByStore getMonthAverageByStore;

    private final CalculateInflationActivity calculateInflationActivity;

    @Override
    public String save(Store store) {
        return this.storeDataProvider.save(store);
    }

    @Override
    public List<Store> query(String name) {
        return this.storeDataProvider.query(name);
    }

    @Override
    public ProductInflation calculateInflation(String storeId, LocalDate startDate, LocalDate endDate) {

        final var firstMonthAveragePrice = this.getMonthAverageByStore.execute(storeId, startDate);

        final var lastMonthAveragePrice =this.getMonthAverageByStore.execute(storeId, endDate);

        final var inflationPercentage = this.calculateInflationActivity.execute(firstMonthAveragePrice, lastMonthAveragePrice);

        return ProductInflation.builder().storeId(storeId).firstPrice(firstMonthAveragePrice).lastPrice(lastMonthAveragePrice)
                .startDate(startDate).endDate(endDate).percentage(inflationPercentage).build();

    }
}
