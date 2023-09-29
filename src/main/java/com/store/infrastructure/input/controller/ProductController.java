package com.store.infrastructure.input.controller;

import com.store.core.model.Attribute;
import com.store.core.model.Product;
import com.store.core.usecases.ProductUseCase;
import com.store.infrastructure.input.controller.requests.ProductRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller("/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    @Post(uri="/", consumes="application/json")
    public HttpResponse<Void> create(@Body ProductRequest productRequest) {

        final var attributeRequest = productRequest.getAttributeRequest();

        final var product = Product.builder()
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .attribute(Attribute.builder().amount(attributeRequest.getAmount())
                        .amountType(attributeRequest.getAmountType()).build())
                .build();

        this.productUseCase.save(product);

        return HttpResponse.noContent();
    }
}