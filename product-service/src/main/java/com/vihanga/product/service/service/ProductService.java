package com.vihanga.product.service.service;

import com.vihanga.product.service.dto.ProductRequest;
import com.vihanga.product.service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
