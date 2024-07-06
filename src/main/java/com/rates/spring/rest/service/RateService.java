package com.rates.spring.rest.service;

import com.rates.spring.rest.entity.RateEntity;

import java.util.UUID;

/**
 * @author Danil Golubenkov
 */
public interface RateService {
    RateEntity getRateById(UUID id);
}

