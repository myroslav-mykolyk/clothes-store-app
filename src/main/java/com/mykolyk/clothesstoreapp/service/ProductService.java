package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.ProductDto;

public interface ProductService {
    ProductDto getProduct(String article);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(String article, ProductDto productDto);

    void deleteProduct(String article);
}
