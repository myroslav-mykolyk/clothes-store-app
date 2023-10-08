package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.GoodDto;

import java.util.List;

public interface GoodService {
    GoodDto getGood(String article);

    List<GoodDto> getAllGoods();

    GoodDto createGood(GoodDto goodDto);

    GoodDto updateGood(String article, GoodDto goodDto);

    void deleteGood(String article);
}
