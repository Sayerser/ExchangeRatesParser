package com.rates.spring.rest.service;

import com.rates.spring.rest.dto.RateDTO;
import com.rates.spring.rest.entity.RateEntity;

import java.util.List;
import java.util.UUID;

public interface RateService {
    RateEntity create(RateDTO dto);
    List<RateEntity> readAll();
    RateEntity update(RateEntity rates);
    void delete(UUID id);
}
