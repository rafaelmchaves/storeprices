package com.store.infrastructure.output.mongo;

import com.store.core.dataprovider.PriceDataProvider;
import com.store.core.model.Price;
import com.store.core.model.ProductType;
import com.store.infrastructure.output.mongo.entity.PriceEntity;
import com.store.infrastructure.output.mongo.mapper.ProductMapper;
import com.store.infrastructure.output.mongo.mapper.StoreMapper;
import com.store.infrastructure.output.mongo.repository.PriceRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Singleton
public class PriceDataProviderImpl implements PriceDataProvider {

    private final PriceRepository priceRepository;

    private final ProductMapper productMapper;

    private final StoreMapper storeMapper;

    @Override
    public void add(Price price) {
        final var priceEntity = PriceEntity.builder().price(price.getPrice())
                .product(productMapper.toProductEmbedded(price.getProduct()))
                .store(storeMapper.toStoreEmbedded(price.getStore()))
                .creationDate(LocalDateTime.now())
                .build();

        priceRepository.save(priceEntity);

    }

    @Override
    public List<Price> findAllByProductIdBetweenDates(String productId, LocalDate startDate, LocalDate endDate) {
        final var priceList = priceRepository.findAllByProductIdBetweenDates(productId, startDate, endDate);

        return buildPriceModel(priceList);
    }

    @Override
    public List<Price> findAllByProductTypeBetweenDates(ProductType productType, LocalDate startDate, LocalDate endDate) {
        final var result = priceRepository.findAllByProductTypeBetweenDates(productType, startDate, endDate);

        return buildPriceModel(result);
    }

    @Override
    public List<Price> findAllByStoreBetweenDates(String storeId, LocalDate startDate, LocalDate endDate) {
       final var result = priceRepository.findAllByStoreIdBetweenDates(storeId, startDate, endDate);

        return buildPriceModel(result);
    }

    private List<Price> buildPriceModel(List<PriceEntity> result) {
        return result.stream().map(price -> Price.builder().price(price.getPrice())
                .product(productMapper.toProductModel(price.getProduct()))
                .store(storeMapper.toStoreModel(price.getStore()))
                .creationDate(price.getCreationDate().toLocalDate())
                .build()).toList();
    }
}
