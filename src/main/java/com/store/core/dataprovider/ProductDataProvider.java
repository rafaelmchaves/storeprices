package com.store.core.dataprovider;

import com.store.core.model.Product;

import java.util.List;

public interface ProductDataProvider {

    String save(Product product);

    List<Product> query(String name);

    Product findById(String id);

}
