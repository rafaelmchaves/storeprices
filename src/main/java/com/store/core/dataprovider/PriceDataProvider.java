package com.store.core.dataprovider;

import com.store.core.model.Price;
import com.store.core.model.ProductType;

import java.time.LocalDate;
import java.util.List;

public interface PriceDataProvider {

    void add(Price price);

    List<Price> findAllByProductIdBetweenDates(String productId, LocalDate startDate, LocalDate endDate);
    List<Price> findAllByProductTypeBetweenDates(ProductType productType, LocalDate startDate, LocalDate endDate);

}
