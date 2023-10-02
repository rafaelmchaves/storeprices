package com.store.infrastructure.input.controller;

import com.store.core.model.Price;
import com.store.core.model.Product;
import com.store.core.model.Store;
import com.store.core.usecases.PriceUseCase;
import com.store.infrastructure.input.controller.requests.PriceRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller("/prices")
public class PriceController {

    private final PriceUseCase priceUseCase;

    @Post(consumes = "application/json")
    public HttpResponse<Void> update(@Body PriceRequest priceRequest) {

        final var price = Price.builder().price(priceRequest.getPrice()).product(
                Product.builder().id(priceRequest.getProductId())
                        .build())
                .store(Store.builder().id(priceRequest.getStoreId()).build())
                .build();

        priceUseCase.add(price);

        return HttpResponse.noContent();
    }
}
