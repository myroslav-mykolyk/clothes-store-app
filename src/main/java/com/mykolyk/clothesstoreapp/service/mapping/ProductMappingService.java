package com.mykolyk.clothesstoreapp.service.mapping;

import com.mykolyk.clothesstoreapp.dto.ProductDto;
import com.mykolyk.clothesstoreapp.model.Product;

public interface ProductMappingService {
    ProductDto mapProductToProductDto(Product product);

    Product mapProductDtoToProduct(ProductDto productDto);

    Product populateProductWithPresentProductDtoFields(Product product, ProductDto productDto);
}
