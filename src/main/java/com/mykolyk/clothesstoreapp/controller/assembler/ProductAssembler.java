package com.mykolyk.clothesstoreapp.controller.assembler;

import com.mykolyk.clothesstoreapp.controller.ProductController;
import com.mykolyk.clothesstoreapp.controller.model.ProductModel;
import com.mykolyk.clothesstoreapp.dto.ProductDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<ProductDto, ProductModel> {
    public static final String GET_REL = "get_product";
    public static final String CREATE_REL = "create_product";
    public static final String UPDATE_REL = "update_product";
    public static final String DELETE_REL = "delete_product";

    public ProductAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(ProductDto entity) {
        ProductModel productModel = new ProductModel(entity);

        Link get = linkTo(methodOn(ProductController.class).getProduct(entity.getArticle())).withRel(GET_REL);
        Link create = linkTo(methodOn(ProductController.class).createProduct(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(ProductController.class).updateProduct(entity.getArticle(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(ProductController.class).deleteProduct(entity.getArticle())).withRel(DELETE_REL);

        productModel.add(get, create, update, delete);

        return productModel;
    }
}
