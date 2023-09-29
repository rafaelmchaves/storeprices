package com.store.infrastructure.input.controller;

import com.store.infrastructure.input.controller.requests.ProductRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/products")
public class ProductController {

    @Post(uri="/", consumes="application/json")
    public HttpResponse<Void> create(@Body ProductRequest productRequest) {

        return HttpResponse.noContent();
    }
}