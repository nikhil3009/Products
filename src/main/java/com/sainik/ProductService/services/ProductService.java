package com.sainik.ProductService.services;

import com.sainik.ProductService.model.ProductRequest;
import com.sainik.ProductService.model.ProductResponse;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
