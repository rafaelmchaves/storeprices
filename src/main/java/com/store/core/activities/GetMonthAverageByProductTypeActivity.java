package com.store.core.activities;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.model.Price;
import com.store.core.model.ProductType;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

@RequiredArgsConstructor
@Singleton
public class GetMonthAverageByProductTypeActivity {

    private final PriceDataProvider priceDataProvider;

    public BigDecimal execute(ProductType productType, LocalDate date) {
        final var monthStartDate = date.withDayOfMonth(1);

        final var monthEndDate = date.withDayOfMonth(date.getMonth().equals(Month.FEBRUARY) ? 28 : 30);

        final var monthPriceList = this.priceDataProvider.findAllByProductTypeBetweenDates(productType, monthStartDate, monthEndDate);

        //TODO throw an exception if the list is empty
        final var price = monthPriceList.stream().map(Price::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return price.divide(BigDecimal.valueOf(monthPriceList.size()), RoundingMode.DOWN);
    }
}
