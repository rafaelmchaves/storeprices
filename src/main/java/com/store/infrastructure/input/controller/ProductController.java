package com.store.infrastructure.input.controller;

import com.store.core.model.Attribute;
import com.store.core.model.Product;
import com.store.core.usecases.ProductUseCase;
import com.store.infrastructure.input.controller.requests.AttributeRequest;
import com.store.infrastructure.input.controller.requests.ProductRequest;
import com.store.infrastructure.input.controller.response.AttributeResponse;
import com.store.infrastructure.input.controller.response.ProductResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller("/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    @Post(consumes="application/json")
    public HttpResponse<Void> create(@Body ProductRequest productRequest) {
        final var attributeRequest = productRequest.getAttributeRequest();

        final var product = buildProduct(productRequest, attributeRequest);

        this.productUseCase.save(product);

        return HttpResponse.noContent();
    }

    @Get(produces = "application/json")
    public HttpResponse<List<ProductResponse>> query(@QueryValue String name) {
        final var foundProducts = this.productUseCase.query(name);

        final var response = foundProducts.stream().map(ProductController::buildProductResponse).toList();

        return HttpResponse.ok(response);
    }

    private static Product buildProduct(ProductRequest productRequest, AttributeRequest attributeRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .type(productRequest.getType())
                .attribute(Attribute.builder().amount(attributeRequest.getAmount())
                        .amountType(attributeRequest.getAmountType()).build())
                .build();
    }

    private static ProductResponse buildProductResponse(Product product) {
        final var attributes = product.getAttribute();

        return ProductResponse.builder().id(product.getId()).name(product.getName()).brand(product.getBrand())
                .type(product.getType())
                .attributes(AttributeResponse.builder().amount(attributes.getAmount())
                        .amountType(attributes.getAmountType()).colours(attributes.getColours())
                        .build())
                .build();
    }
}