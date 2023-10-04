package com.store.core.dataprovider;

import com.store.core.model.Price;

import java.time.LocalDate;
import java.util.List;

public interface PriceDataProvider {

    void add(Price price);

    List<Price> findAllByProductIdBetweenDates(String productId, LocalDate startDate, LocalDate endDate);

}
