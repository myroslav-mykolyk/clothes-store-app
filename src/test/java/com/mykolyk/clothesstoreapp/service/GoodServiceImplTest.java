package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.exception.GoodAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.GoodNotFoundException;
import com.mykolyk.clothesstoreapp.model.Good;
import com.mykolyk.clothesstoreapp.repository.GoodRepository;
import com.mykolyk.clothesstoreapp.service.impl.GoodServiceImpl;
import com.mykolyk.clothesstoreapp.service.mapping.GoodMappingService;
import com.mykolyk.clothesstoreapp.service.mapping.impl.GoodMappingServiceImpl;
import com.mykolyk.clothesstoreapp.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.mykolyk.clothesstoreapp.test.util.TestDataUtil.ARTICLE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodServiceImplTest {
    @InjectMocks
    private GoodServiceImpl GoodService;

    @Spy
    private final GoodMappingService goodMappingService = new GoodMappingServiceImpl();

    @Mock
    private GoodRepository goodRepository;

    @Test
    void getGoodTest() {
        Good testGood = TestDataUtil.createGood();
        when(goodRepository.findByArticle(ARTICLE)).thenReturn(Optional.of(testGood));

        GoodDto goodDto = GoodService.getGood(ARTICLE);

        assertThat(goodDto, allOf(
                hasProperty("name", equalTo(testGood.getName())),
                hasProperty("article", equalTo(testGood.getArticle())),
                hasProperty("price", equalTo(testGood.getPrice())),
                hasProperty("quantity", equalTo(testGood.getQuantity()))
        ));
    }

    @Test
    void getAllGoodsTest() {
        Good testGood = TestDataUtil.createGood();
        when(goodRepository.findAll()).thenReturn(List.of(testGood));

        List<GoodDto> goodDtos = GoodService.getAllGoods();

        assertThat(goodDtos.get(goodDtos.size()-1), allOf(
                hasProperty("name", equalTo(testGood.getName())),
                hasProperty("article", equalTo(testGood.getArticle())),
                hasProperty("price", equalTo(testGood.getPrice())),
                hasProperty("quantity", equalTo(testGood.getQuantity()))
        ));
    }

    @Test
    void getGoodGoodNotFoundTest() {
        when(goodRepository.findByArticle(ARTICLE)).thenReturn(Optional.empty());
        assertThrows(GoodNotFoundException.class, () -> GoodService.getGood(ARTICLE));
    }

    @Test
    public void createGoodTest() {
        Good testGood = TestDataUtil.createGood();
        GoodDto testGoodDto = TestDataUtil.createGoodDto();
        when(goodRepository.save(any())).thenReturn(testGood);

        GoodDto goodDto = GoodService.createGood(testGoodDto);

        assertThat(goodDto, allOf(
                hasProperty("name", equalTo(testGood.getName())),
                hasProperty("article", equalTo(testGood.getArticle())),
                hasProperty("price", equalTo(testGood.getPrice())),
                hasProperty("quantity", equalTo(testGood.getQuantity()))
        ));
    }

    @Test
    public void createGoodGoodAlreadyExistsTest() {
        GoodDto testGoodDto = TestDataUtil.createGoodDto();
        when(goodRepository.existsByArticle(ARTICLE)).thenReturn(true);

        assertThrows(GoodAlreadyExistException.class, () -> GoodService.createGood(testGoodDto));
    }

    @Test
    public void updateGoodTest() {
        GoodDto testGoodDto = TestDataUtil.createGoodDto();
        Good testGood = TestDataUtil.createGood();
        when(goodRepository.findByArticle(testGoodDto.getArticle())).thenReturn(Optional.of(testGood));
        when(goodRepository.save(any())).thenReturn(testGood);

        GoodDto GoodDto = GoodService.updateGood(testGood.getArticle(), testGoodDto);

        assertThat(GoodDto, allOf(
                hasProperty("name", equalTo(testGood.getName())),
                hasProperty("article", equalTo(testGood.getArticle())),
                hasProperty("price", equalTo(testGood.getPrice())),
                hasProperty("quantity", equalTo(testGood.getQuantity()))
        ));
    }

    @Test
    public void updateGoodGoodNotFoundTest() {
        GoodDto testGoodDto = TestDataUtil.createGoodDto();
        when(goodRepository.findByArticle(ARTICLE)).thenReturn(Optional.empty());

        assertThrows(GoodNotFoundException.class,
                () -> GoodService.updateGood(testGoodDto.getArticle(), testGoodDto));
    }

    @Test
    void deleteGoodTest() {
        Good testGood = TestDataUtil.createGood();
        when(goodRepository.findByArticle(ARTICLE)).thenReturn(Optional.of(testGood));

        GoodService.deleteGood(testGood.getArticle());

        verify(goodRepository, times(1)).delete(testGood);
    }

    @Test
    void deleteGoodGoodNotFoundTest() {
        when(goodRepository.findByArticle(ARTICLE)).thenReturn(Optional.empty());
        assertThrows(GoodNotFoundException.class, () -> GoodService.deleteGood(ARTICLE));
    }
}
