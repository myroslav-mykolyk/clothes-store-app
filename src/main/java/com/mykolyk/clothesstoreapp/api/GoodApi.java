package com.mykolyk.clothesstoreapp.api;

import com.mykolyk.clothesstoreapp.controller.model.GoodModel;
import com.mykolyk.clothesstoreapp.dto.GoodDto;
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

@Api(tags = "Good management API")
@RequestMapping("/api/v1/goods")
public interface GoodApi {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "good", paramType = "path", required = true, value = "Good article"),
    })
    @ApiOperation("Get good")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{article}")
    GoodModel getGood(@PathVariable String article);

    @ApiOperation("Create good")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    GoodModel createGood(@Validated(OnCreate.class) @RequestBody GoodDto goodDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", paramType = "path", required = true, value = "Good article"),
    })
    @ApiOperation("Update good")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{article}")
    GoodModel updateGood(@PathVariable String article, @Validated(OnUpdate.class) @RequestBody GoodDto goodDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", paramType = "path", required = true, value = "Good article"),
    })
    @ApiOperation("Delete good")
    @DeleteMapping(value = "/{article}")
    ResponseEntity<Void> deleteGood(@PathVariable String article);
}
