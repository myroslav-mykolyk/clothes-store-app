package com.mykolyk.clothesstoreapp.api;

import com.mykolyk.clothesstoreapp.controller.model.ProductModel;
import com.mykolyk.clothesstoreapp.dto.ProductDto;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Product management API")
@RequestMapping("/api/v1/products")
public interface ProductApi {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product", paramType = "path", required = true, value = "Product article"),
    })
    @ApiOperation("Get product")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{article}")
    ProductModel getProduct(@PathVariable String article);

    @ApiOperation("Create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ProductModel createProduct(@Validated(OnCreate.class) @RequestBody ProductDto productDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", paramType = "path", required = true, value = "Product article"),
    })
    @ApiOperation("Update product")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{article}")
    ProductModel updateProduct(@PathVariable String article, @Validated(OnUpdate.class) @RequestBody ProductDto productDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", paramType = "path", required = true, value = "Product article"),
    })
    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{article}")
    ResponseEntity<Void> deleteProduct(@PathVariable String article);
}
