package com.store.core.usecases;

import com.store.core.model.ProductInflation;
import com.store.core.model.Store;
import com.store.infrastructure.input.controller.response.ProductInflationResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface StoreUseCase {

    String save(Store store);

    List<Store> query(String name);

    ProductInflation calculateInflation(String storeId, LocalDate startDate, LocalDate endDate);
}
