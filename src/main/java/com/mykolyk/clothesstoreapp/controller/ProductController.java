package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.api.ProductApi;
import com.mykolyk.clothesstoreapp.controller.assembler.ProductAssembler;
import com.mykolyk.clothesstoreapp.controller.model.ProductModel;
import com.mykolyk.clothesstoreapp.dto.ProductDto;
import com.mykolyk.clothesstoreapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {
    private final ProductAssembler productAssembler;
    private final ProductService productService;

    @Override
    public ProductModel getProduct(String article) {
        ProductDto outProductDto = productService.getProduct(article);
        return productAssembler.toModel(outProductDto);
    }

    @Override
    public ProductModel createProduct(ProductDto productDto) {
        ProductDto outProductDto = productService.createProduct(productDto);
        return productAssembler.toModel(outProductDto);
    }

    @Override
    public ProductModel updateProduct(String article, ProductDto productDto) {
        ProductDto outProductDto = productService.updateProduct(article, productDto);
        return productAssembler.toModel(outProductDto);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String article) {
        productService.deleteProduct(article);
        return ResponseEntity.noContent().build();
    }
}
