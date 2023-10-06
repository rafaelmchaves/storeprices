package com.store.core.usecases;

import com.store.core.model.Product;
import com.store.core.model.ProductInflation;
import com.store.core.model.ProductType;

import java.time.LocalDate;
import java.util.List;

public interface ProductUseCase {

    String save(Product product);

    List<Product> query(String name);

    ProductInflation calculateInflation(final String id, final LocalDate startDate, final LocalDate endDate);

    ProductInflation calculateInflation(final ProductType productType, final LocalDate startDate, final LocalDate endDate);
}
