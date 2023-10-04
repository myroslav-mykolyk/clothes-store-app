package com.mykolyk.clothesstoreapp.service.mapping.impl;

import com.mykolyk.clothesstoreapp.dto.ProductDto;
import com.mykolyk.clothesstoreapp.model.Product;
import com.mykolyk.clothesstoreapp.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductMappingServiceImpl implements ProductMappingService {
    @Override
    public ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .article(product.getArticle())
                .price(product.getPrice())
                .build();
    }

    @Override
    public Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .article(productDto.getArticle())
                .price(productDto.getPrice())
                .build();
    }

    @Override
    public Product populateProductWithPresentProductDtoFields(Product product, ProductDto productDto) {
        String name = productDto.getName();
        if (Objects.nonNull(name)) {
            product.setName(name);
        }
        return product;
    }
}
