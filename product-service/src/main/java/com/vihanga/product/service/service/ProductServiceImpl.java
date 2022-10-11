package com.vihanga.product.service.service;

import com.vihanga.product.service.dto.ProductRequest;
import com.vihanga.product.service.dto.ProductResponse;
import com.vihanga.product.service.model.Product;
import com.vihanga.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = productRequestToProduct(productRequest);
        productRepository.save(product);

        log.info("This Product id was created: {}",product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
    List<Product> productList=    productRepository.findAll();
    return productList.stream().map(product -> productToProductResponse(product)).collect(Collectors.toList());
    }

    private Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    private ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
