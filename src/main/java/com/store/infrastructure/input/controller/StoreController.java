package com.store.infrastructure.input.controller;

import com.store.core.model.Store;
import com.store.core.usecases.StoreUseCase;
import com.store.infrastructure.input.controller.requests.ProductRequest;
import com.store.infrastructure.input.controller.requests.StoreCreationRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller("/stores")
public class StoreController {

    private final StoreUseCase useCase;

    @Post(consumes="application/json")
    public HttpResponse<Void> create(@Body StoreCreationRequest storeCreationRequest) {

        final var store = Store.builder().name(storeCreationRequest.getName()).type(storeCreationRequest.getType())
                .city(storeCreationRequest.getCity()).build();

        this.useCase.save(store);

        return HttpResponse.noContent();
    }
}
