package com.mykolyk.clothesstoreapp.api;

import com.mykolyk.clothesstoreapp.controller.model.OrderItemModel;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
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

@Api(tags = "Order item management API")
@RequestMapping("/api/v1/order-items")
public interface OrderItemApi {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order item id"),
    })
    @ApiOperation("Get order item")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    OrderItemModel getOrderItem(@PathVariable Long id);

    @ApiOperation("Create order item")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderItemModel createOrderItem(@Validated(OnCreate.class) @RequestBody OrderItemDto orderItemDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order item id"),
    })
    @ApiOperation("Update order item")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    OrderItemModel updateOrderItem(@PathVariable Long id,  @Validated(OnUpdate.class) @RequestBody OrderItemDto orderItemDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order item id"),
    })
    @ApiOperation("Delete order item")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteOrderItem(@PathVariable Long id);
}
