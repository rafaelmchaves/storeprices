package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Attribute;
import com.store.core.model.Product;
import com.store.infrastructure.output.mongo.entity.AttributeEmbedded;
import com.store.infrastructure.output.mongo.entity.ProductEntity;
import com.store.infrastructure.output.mongo.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @Override
    public List<Product> query(String name) {

        final var result = productRepository.findByNameLike(name);

        return result.stream().map(productEntity -> {
            final var attributeResult = productEntity.getAttributes();
            return Product.builder()
                    .id(productEntity.getId().toString())
                    .name(productEntity.getName()).brand(productEntity.getBrand())
                    .attribute(Attribute.builder().amountType(attributeResult.getAmountType())
                            .amount(attributeResult.getAmount())
                            .colours(attributeResult.getColours())
                            .build())
                    .build();
        }).toList();

    }
}
