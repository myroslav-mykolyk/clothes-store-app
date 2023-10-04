package com.mykolyk.clothesstoreapp.service.mapping;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.model.Good;

public interface GoodMappingService {
    GoodDto mapGoodToGoodDto(Good good);

    Good mapGoodDtoToGood(GoodDto goodDto);

    Good populateGoodWithPresentGoodDtoFields(Good good, GoodDto goodDto);
}
