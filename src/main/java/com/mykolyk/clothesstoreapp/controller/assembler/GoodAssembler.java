package com.mykolyk.clothesstoreapp.controller.assembler;

import com.mykolyk.clothesstoreapp.controller.GoodController;
import com.mykolyk.clothesstoreapp.controller.model.GoodModel;
import com.mykolyk.clothesstoreapp.dto.GoodDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GoodAssembler extends RepresentationModelAssemblerSupport<GoodDto, GoodModel> {
    public static final String GET_REL = "get_good";
    public static final String GET_ALL_REL = "get_all_goods";
    public static final String CREATE_REL = "create_good";
    public static final String UPDATE_REL = "update_good";
    public static final String DELETE_REL = "delete_good";

    public GoodAssembler() {
        super(GoodController.class, GoodModel.class);
    }

    @Override
    public GoodModel toModel(GoodDto entity) {
        GoodModel goodModel = new GoodModel(entity);

        Link get = linkTo(methodOn(GoodController.class).getGood(entity.getArticle())).withRel(GET_REL);
        Link getAll = linkTo(methodOn(GoodController.class).getAllGoods()).withRel(GET_ALL_REL);
        Link create = linkTo(methodOn(GoodController.class).createGood(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(GoodController.class).updateGood(entity.getArticle(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(GoodController.class).deleteGood(entity.getArticle())).withRel(DELETE_REL);

        goodModel.add(get, getAll, create, update, delete);

        return goodModel;
    }
}
