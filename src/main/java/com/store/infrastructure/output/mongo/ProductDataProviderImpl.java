package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Product;
import com.store.infrastructure.output.mongo.entity.AttributeEmbedded;
import com.store.infrastructure.output.mongo.entity.ProductEntity;
import com.store.infrastructure.output.mongo.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Singleton
public class ProductDataProviderImpl implements ProductDataProvider {

    private final ProductRepository productRepository;
    @Override
    public void save(Product product) {

        final var entity = ProductEntity.builder().name(product.getName()).brand(product.getBrand())
                .attributes(AttributeEmbedded.builder().amountType(product.getAttribute().getAmountType())
                        .amount(product.getAttribute().getAmount())
                        .build())
                .build();

        productRepository.save(entity);
    }
}
