package com.mykolyk.clothesstoreapp.controller.assembler;

import com.mykolyk.clothesstoreapp.controller.OrderItemController;
import com.mykolyk.clothesstoreapp.controller.model.OrderItemModel;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemAssembler extends RepresentationModelAssemblerSupport<OrderItemDto, OrderItemModel> {
    public static final String GET_REL = "get_order_item";
    public static final String CREATE_REL = "create_order_item";
    public static final String UPDATE_REL = "update_order_item";
    public static final String DELETE_REL = "delete_order_item";

    public OrderItemAssembler() {
        super(OrderItemController.class, OrderItemModel.class);
    }

    @Override
    public OrderItemModel toModel(OrderItemDto entity) {
        OrderItemModel orderItemModel = new OrderItemModel(entity);

        Link get = linkTo(methodOn(OrderItemController.class).getOrderItem(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(OrderItemController.class).createOrderItem(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(OrderItemController.class).updateOrderItem(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(OrderItemController.class).deleteOrderItem(entity.getId())).withRel(DELETE_REL);

        orderItemModel.add(get, create, update, delete);

        return orderItemModel;
    }
}
