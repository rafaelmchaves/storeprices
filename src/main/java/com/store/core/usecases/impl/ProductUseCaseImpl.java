package com.store.core.usecases.impl;

import com.store.core.activities.CalculateInflationActivity;
import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Price;
import com.store.core.model.Product;
import com.store.core.model.ProductInflation;
import com.store.core.model.ProductType;
import com.store.core.usecases.ProductUseCase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public ProductInflation calculateInflation(ProductType productType, LocalDate startDate, LocalDate endDate) {

        final var firstMonthAveragePrice = getMonthAverageByProductType(productType, startDate);

        final var lastMonthAveragePrice = getMonthAverageByProductType(productType, endDate);

        final var inflationPercentage = calculateInflationActivity.execute(firstMonthAveragePrice, lastMonthAveragePrice);

        return ProductInflation.builder().productType(productType).firstPrice(firstMonthAveragePrice).lastPrice(lastMonthAveragePrice)
                .startDate(startDate).endDate(endDate)
                .percentage(inflationPercentage).build();
    }

    private BigDecimal getMonthAverageByProductType(ProductType productType, LocalDate date) {
        final var monthStartDate = date.withDayOfMonth(1);
        final var monthEndDate = date.withDayOfMonth(30);

        final var firstMontPriceList = this.priceDataProvider.findAllByProductTypeBetweenDates(productType, monthStartDate, monthEndDate);

        final var price = firstMontPriceList.stream().map(Price::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return price.divide(BigDecimal.valueOf(firstMontPriceList.size()), RoundingMode.DOWN);
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
