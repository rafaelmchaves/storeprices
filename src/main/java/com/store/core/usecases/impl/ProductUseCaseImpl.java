package com.store.core.usecases.impl;

import com.store.core.activities.CalculateInflationActivity;
import com.store.core.activities.CheckIfDataIsValidToCalculateProductInflationActivity;
import com.store.core.activities.GetMonthAverageByProductTypeActivity;
import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Product;
import com.store.core.model.ProductInflation;
import com.store.core.model.ProductType;
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

    private final CalculateInflationActivity calculateInflationActivity;

    private final GetMonthAverageByProductTypeActivity getMonthAverageByProductTypeActivity;

    private final CheckIfDataIsValidToCalculateProductInflationActivity checkIfDataIsValidToCalculateProductInflation;

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

        final var priceList = this.priceDataProvider.findAllByProductIdBetweenDates(id, startDate, endDate);

        this.checkIfDataIsValidToCalculateProductInflation.execute(priceList);

        final var firstPrice = priceList.get(0).getPrice();
        final var lastPrice = priceList.get(priceList.size() - 1).getPrice();

        final var inflationPercentage = this.calculateInflationActivity.execute(firstPrice, lastPrice);

        return ProductInflation.builder().productId(id).firstPrice(firstPrice).lastPrice(lastPrice)
                .startDate(startDate).endDate(endDate)
                .percentage(inflationPercentage).build();
    }

    @Override
    public ProductInflation calculateInflation(ProductType productType, LocalDate startDate, LocalDate endDate) {

        final var firstMonthAveragePrice = this.getMonthAverageByProductTypeActivity.execute(productType, startDate);

        final var lastMonthAveragePrice = this.getMonthAverageByProductTypeActivity.execute(productType, endDate);

        final var inflationPercentage = this.calculateInflationActivity.execute(firstMonthAveragePrice, lastMonthAveragePrice);

        return ProductInflation.builder().productType(productType).firstPrice(firstMonthAveragePrice).lastPrice(lastMonthAveragePrice)
                .startDate(startDate).endDate(endDate)
                .percentage(inflationPercentage).build();
    }

}
