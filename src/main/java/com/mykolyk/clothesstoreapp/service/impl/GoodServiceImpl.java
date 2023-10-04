package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.exception.GoodAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.GoodNotFoundException;
import com.mykolyk.clothesstoreapp.model.Good;
import com.mykolyk.clothesstoreapp.repository.GoodRepository;
import com.mykolyk.clothesstoreapp.service.GoodService;
import com.mykolyk.clothesstoreapp.service.mapping.GoodMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;
    private final GoodMappingService goodMappingService;

    @Override
    public GoodDto getGood(String article) {
        log.info("Getting good by article: {}", article);
        Good good = goodRepository.findByArticle(article).orElseThrow(GoodNotFoundException::new);
        log.info("Founded good with article: {}", article);
        return goodMappingService.mapGoodToGoodDto(good);
    }

    @Override
    @Transactional
    public GoodDto createGood(GoodDto goodDto) {
        log.info("Creating good with article: {}", goodDto.getArticle());
        if (goodRepository.existsByArticle(goodDto.getArticle())) {
            throw new GoodAlreadyExistException();
        }
        Good good = goodMappingService.mapGoodDtoToGood(goodDto);
        good = goodRepository.save(good);
        log.info("Created good with article: {}", good.getArticle());
        return goodMappingService.mapGoodToGoodDto(good);
    }

    @Override
    @Transactional
    public GoodDto updateGood(String article, GoodDto goodDto) {
        log.info("Updating good with article: {}", article);
        Good persistedGood = goodRepository.findByArticle(article).orElseThrow(GoodNotFoundException::new);
        persistedGood = goodMappingService.populateGoodWithPresentGoodDtoFields(persistedGood, goodDto);
        Good storedGood = goodRepository.save(persistedGood);
        log.info("Updated good with article: {}", storedGood.getArticle());
        return goodMappingService.mapGoodToGoodDto(persistedGood);
    }

    @Override
    @Transactional
    public void deleteGood(String article) {
        log.info("Deleting good with article: {}", article);
        Good good = goodRepository.findByArticle(article).orElseThrow(GoodNotFoundException::new);
        goodRepository.delete(good);
        log.info("Deleted good with article: {}", article);
    }
}
