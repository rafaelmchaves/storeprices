package com.store.infrastructure.output.mongo.mapper;

import com.store.core.model.Attribute;
import com.store.core.model.Product;
import com.store.infrastructure.output.mongo.entity.AttributeEmbedded;
import com.store.infrastructure.output.mongo.entity.ProductEmbedded;
import com.store.infrastructure.output.mongo.entity.ProductEntity;
import jakarta.inject.Singleton;

@Singleton
public class ProductMapper {

    public ProductEntity toProductEntity(final Product product) {
        return ProductEntity.builder().name(product.getName()).brand(product.getBrand())
                .type(product.getType())
                .attributes(AttributeEmbedded.builder().amountType(product.getAttribute().getAmountType())
                        .amount(product.getAttribute().getAmount())
                        .build())
                .build();
    }

    public Product toProductModel(ProductEntity productEntity) {
        final var attributeResult = productEntity.getAttributes();
        return Product.builder()
                .id(productEntity.getId().toString())
                .name(productEntity.getName()).brand(productEntity.getBrand())
                .type(productEntity.getType())
                .attribute(Attribute.builder().amountType(attributeResult.getAmountType())
                        .amount(attributeResult.getAmount())
                        .colours(attributeResult.getColours())
                        .build())
                .build();
    }

    public ProductEmbedded toProductEmbedded(final Product product) {
        return ProductEmbedded.builder().id(product.getId()).name(product.getName()).brand(product.getBrand())
                .type(product.getType())
                .attributes(AttributeEmbedded.builder().amountType(product.getAttribute().getAmountType())
                        .amount(product.getAttribute().getAmount())
                        .build())
                .build();
    }

}
