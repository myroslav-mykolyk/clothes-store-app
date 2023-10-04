package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.GoodDto;

public interface GoodService {
    GoodDto getGood(String article);

    GoodDto createGood(GoodDto goodDto);

    GoodDto updateGood(String article, GoodDto goodDto);

    void deleteGood(String article);
}
