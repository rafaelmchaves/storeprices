package com.store.core.usecases.impl;

import com.store.core.activities.CalculateInflationActivity;
import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Price;
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

    private final CalculateInflationActivity calculateInflationActivity;

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

        checkIfDataIsValidToCalculateProductInflation(priceList);

        final var firstPrice = priceList.get(0).getPrice();
        final var lastPrice = priceList.get(priceList.size() - 1).getPrice();

        final var inflationPercentage = calculateInflationActivity.execute(firstPrice, lastPrice);

        return ProductInflation.builder().productId(id).firstPrice(firstPrice).lastPrice(lastPrice)
                .startDate(startDate).endDate(endDate)
                .percentage(inflationPercentage).build();
    }

    private void checkIfDataIsValidToCalculateProductInflation(List<Price> priceList) {
        if (priceList.isEmpty() || priceList.size() == 1) {
            throw new RuntimeException("There is no enough data of prices to compare");
        }

        final Price firstPriceModel = priceList.get(0);
        final Price lastPriceModel = priceList.get(priceList.size() - 1);

        if (firstPriceModel.getCreationDate().getMonthValue() == lastPriceModel.getCreationDate().getMonthValue()) {
            throw new RuntimeException("There is no enough data of prices to compare, it's necessary at least 2 months to compare the inflation");
        }
    }

}
