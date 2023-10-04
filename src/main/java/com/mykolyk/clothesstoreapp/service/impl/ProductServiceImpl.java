package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.ProductDto;
import com.mykolyk.clothesstoreapp.exception.ProductAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.ProductNotFoundException;
import com.mykolyk.clothesstoreapp.model.Product;
import com.mykolyk.clothesstoreapp.repository.ProductRepository;
import com.mykolyk.clothesstoreapp.service.ProductService;
import com.mykolyk.clothesstoreapp.service.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMappingService productMappingService;

    @Override
    public ProductDto getProduct(String article) {
        log.info("Getting product by article: {}", article);
        Product product = productRepository.findByArticle(article).orElseThrow(ProductNotFoundException::new);
        log.info("Founded product with article: {}", article);
        return productMappingService.mapProductToProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Creating product with article: {}", productDto.getArticle());
        if (productRepository.existsByArticle(productDto.getArticle())) {
            throw new ProductAlreadyExistException();
        }
        Product product = productMappingService.mapProductDtoToProduct(productDto);
        product = productRepository.save(product);
        log.info("Created product with article: {}", product.getArticle());
        return productMappingService.mapProductToProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String article, ProductDto productDto) {
        log.info("Updating product with article: {}", article);
        Product persistedProduct = productRepository.findByArticle(article).orElseThrow(ProductNotFoundException::new);
        persistedProduct = productMappingService.populateProductWithPresentProductDtoFields(persistedProduct, productDto);
        Product storedProduct = productRepository.save(persistedProduct);
        log.info("Updated product with article: {}", storedProduct.getArticle());
        return productMappingService.mapProductToProductDto(persistedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(String article) {
        log.info("Deleting product with article: {}", article);
        Product product = productRepository.findByArticle(article).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
        log.info("Deleted product with article: {}", article);
    }
}
