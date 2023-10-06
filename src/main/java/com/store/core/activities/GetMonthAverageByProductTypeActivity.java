package com.store.core.activities;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.exceptions.NotEnoughDataException;
import com.store.core.model.Price;
import com.store.core.model.ProductType;
import com.store.infrastructure.output.exceptions.ErrorCode;
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

        if (monthPriceList.isEmpty()) {
            final var errorMessage = String.format("There is no enough data for the productType: %s, date: %s", productType, date);
            throw new NotEnoughDataException(errorMessage, ErrorCode.COD03.name());
        }

        final var price = monthPriceList.stream().map(Price::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return price.divide(BigDecimal.valueOf(monthPriceList.size()), RoundingMode.DOWN);
    }
}
