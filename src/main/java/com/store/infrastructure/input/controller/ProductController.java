package com.store.infrastructure.input.controller;

import com.store.infrastructure.input.controller.requests.ProductRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/products")
public class ProductController {

    @Put(uri="/{id}", consumes="application/json")
    public HttpResponse<Void> update(@Body ProductRequest productRequest, @PathVariable String id) {

        log.info("productRequest name: {}, price: {}, storeName: {} ", productRequest.getName(), productRequest.getPrice(),
                productRequest.getStore().getName());

        return HttpResponse.noContent();
    }
}