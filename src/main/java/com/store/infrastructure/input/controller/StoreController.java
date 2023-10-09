package com.store.infrastructure.input.controller;

import com.store.core.model.ProductInflation;
import com.store.core.model.Store;
import com.store.core.usecases.StoreUseCase;
import com.store.infrastructure.input.controller.requests.StoreCreationRequest;
import com.store.infrastructure.input.controller.response.ProductInflationResponse;
import com.store.infrastructure.input.controller.response.StoreResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller("/stores")
public class StoreController {

    private final StoreUseCase useCase;

    @Post(consumes="application/json")
    public MutableHttpResponse<Object> create(@Body StoreCreationRequest storeCreationRequest) {
        final var store = Store.builder().name(storeCreationRequest.getName()).type(storeCreationRequest.getType())
                .city(storeCreationRequest.getCity()).build();

        final var storeId = this.useCase.save(store);

        return HttpResponse.status(HttpStatus.CREATED).header("store_id", storeId);
    }

    @Get(produces = "application/json")
    public HttpResponse<List<StoreResponse>> query(@QueryValue String name) {
        final var storeList = this.useCase.query(name);

        final var storeResponse = storeList.stream().map(store ->
                    StoreResponse.builder().id(store.getId())
                    .name(store.getName())
                    .type(store.getType()).build())
                .toList();

        return HttpResponse.ok(storeResponse);
    }

    @Get(produces = "application/json", value = "/{id}/calculate-inflation")
    public HttpResponse<ProductInflationResponse> calculateInflation(@PathVariable String id, @QueryValue LocalDate startDate,
                                                                     @QueryValue LocalDate endDate) {

        final var productInflation = useCase.calculateInflation(id, startDate, endDate);
        final var response = ProductInflationResponse.builder().percentage(productInflation.getPercentage())
                .startDate(productInflation.getStartDate()).endDate(productInflation.getEndDate()).build();

        return HttpResponse.ok(response);
    }
}
