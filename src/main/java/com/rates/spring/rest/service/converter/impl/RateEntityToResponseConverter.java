package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.dto.response.RateResponse;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.service.converter.ConverterService;
import org.springframework.stereotype.Service;

@Service
public class RateEntityToResponseConverter implements ConverterService<RateEntity, RateResponse> {

    @Override
    public RateResponse convert(RateEntity source) {
        if (source == null) {
            return null;
        }

        RateResponse response = new RateResponse();

        response.setId(source.getId());
        response.setCurrencyId(source.getCurrencyId());
        response.setNominal(source.getNominal());
        response.setValue(source.getValue());
        response.setRateDate(source.getRateDate());

        return response;
    }
}
