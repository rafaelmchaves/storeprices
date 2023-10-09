package com.store.core.activities;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.exceptions.NotEnoughDataException;
import com.store.core.model.Price;
import com.store.infrastructure.output.exceptions.ErrorCode;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@RequiredArgsConstructor
@Singleton
public class GetMonthAverageByStore {

    private final PriceDataProvider priceDataProvider;

    private final GetMonthStartDateActivity getMonthStartDateActivity;

    private final GetMonthEndDateActivity getMonthEndDateActivity;

    public BigDecimal execute(String storeId, LocalDate date) {
        final var monthStartDate = getMonthStartDateActivity.execute(date);
        final var monthEndDate = getMonthEndDateActivity.execute(date);

        final var priceList = priceDataProvider.findAllByStoreBetweenDates(storeId, monthStartDate, monthEndDate);

        if (priceList.isEmpty()) {
            final var errorMessage = String.format("There is no enough data for the store - id: %s, date: %s", storeId, date);
            throw new NotEnoughDataException(errorMessage, ErrorCode.COD03.name());
        }

        final var price = priceList.stream().map(Price::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        return price.divide(BigDecimal.valueOf(priceList.size()), RoundingMode.DOWN);
    }
}
