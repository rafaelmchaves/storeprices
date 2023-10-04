package com.store.core.activities;

import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Singleton
public class CalculateInflationActivity {

    public BigDecimal execute(BigDecimal firstPrice, BigDecimal lastPrice) {
        return (lastPrice.subtract(firstPrice))
                .divide(lastPrice, RoundingMode.DOWN)
                .multiply(BigDecimal.valueOf(100));
    }
}
