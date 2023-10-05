package com.mykolyk.clothesstoreapp.api;

import com.mykolyk.clothesstoreapp.controller.model.OrderModel;
import com.mykolyk.clothesstoreapp.dto.OrderDto;
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

@Api(tags = "Order management API")
@RequestMapping("/api/v1/orders")
public interface OrderApi {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id"),
    })
    @ApiOperation("Get order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    OrderModel getOrder(@PathVariable Long id);

    @ApiOperation("Create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderModel createOrder(@Validated(OnCreate.class) @RequestBody OrderDto orderDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id"),
    })
    @ApiOperation("Update order")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    OrderModel updateOrder(@PathVariable Long id,  @Validated(OnUpdate.class) @RequestBody OrderDto orderDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id"),
    })
    @ApiOperation("Delete order")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);
}
