package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.api.GoodApi;
import com.mykolyk.clothesstoreapp.controller.assembler.GoodAssembler;
import com.mykolyk.clothesstoreapp.controller.model.GoodModel;
import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoodController implements GoodApi {
    private final GoodAssembler goodAssembler;
    private final GoodService goodService;

    @Override
    public GoodModel getGood(String article) {
        GoodDto outGoodDto = goodService.getGood(article);
        return goodAssembler.toModel(outGoodDto);
    }

    @Override
    public GoodModel createGood(GoodDto goodDto) {
        GoodDto outGoodDto = goodService.createGood(goodDto);
        return goodAssembler.toModel(outGoodDto);
    }

    @Override
    public GoodModel updateGood(String article, GoodDto goodDto) {
        GoodDto outGoodDto = goodService.updateGood(article, goodDto);
        return goodAssembler.toModel(outGoodDto);
    }

    @Override
    public ResponseEntity<Void> deleteGood(String article) {
        goodService.deleteGood(article);
        return ResponseEntity.noContent().build();
    }
}
