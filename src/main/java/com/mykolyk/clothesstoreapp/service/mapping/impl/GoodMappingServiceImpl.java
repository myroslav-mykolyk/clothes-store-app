package com.mykolyk.clothesstoreapp.service.mapping.impl;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.model.Good;
import com.mykolyk.clothesstoreapp.service.mapping.GoodMappingService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GoodMappingServiceImpl implements GoodMappingService {
    @Override
    public GoodDto mapGoodToGoodDto(Good good) {
        return GoodDto.builder()
                .name(good.getName())
                .article(good.getArticle())
                .price(good.getPrice())
                .quantity(good.getQuantity())
                .build();
    }

    @Override
    public Good mapGoodDtoToGood(GoodDto goodDto) {
        return Good.builder()
                .name(goodDto.getName())
                .article(goodDto.getArticle())
                .price(goodDto.getPrice())
                .quantity(goodDto.getQuantity())
                .build();
    }

    @Override
    public Good populateGoodWithPresentGoodDtoFields(Good good, GoodDto goodDto) {
        String name = goodDto.getName();
        if (Objects.nonNull(name)) {
            good.setName(name);
        }
        return good;
    }
}
