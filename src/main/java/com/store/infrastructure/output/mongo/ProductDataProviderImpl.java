package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.ProductDataProvider;
import com.store.core.model.Product;
import com.store.infrastructure.output.mongo.mapper.ProductMapper;
import com.store.infrastructure.output.mongo.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@RequiredArgsConstructor
@Singleton
public class ProductDataProviderImpl implements ProductDataProvider {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public String save(Product product) {
        final var entity = productMapper.toProductEntity(product);

        return productRepository.save(entity).getId().toString();
    }

    @Override
    public List<Product> query(String name) {
        final var result = productRepository.findByNameLike(name);

        return result.stream().map(productMapper::toProductModel).toList();

    }

    @Override
    public Product findById(String id) {
        final var result = productRepository.findById(new ObjectId(id));
        if (result.isPresent()) {
            final var entity = result.get();
            return productMapper.toProductModel(entity);
        }

        return null;
    }
}
