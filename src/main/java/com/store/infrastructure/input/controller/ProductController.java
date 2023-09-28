package com.store.infrastructure.input.controller;

import com.store.infrastructure.input.controller.requests.ProductRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller("/products")
public class ProductController {

    @Put(uri="/{id}", consumes="json/application")
    public HttpResponse<Void> update(@RequestBody(required = true, useParameterTypeSchema = true, description = "Product to be updated")
                                         ProductRequest productRequest, @PathVariable String id) {

        return HttpResponse.noContent();
    }
}