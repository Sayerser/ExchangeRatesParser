package com.rates.spring.rest.service;

import com.rates.spring.rest.dto.RateDictDTO;
import com.rates.spring.rest.entity.RateDictEntity;

import java.util.List;
import java.util.UUID;

public interface RateDictService {
    RateDictEntity create(RateDictDTO dto);
    List<RateDictEntity> readAll();
    RateDictEntity update(RateDictEntity rateDict);
    void delete(UUID id);
}
