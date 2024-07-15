package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.dto.response.CountryResponse;
import com.rates.spring.rest.dto.response.RateDictResponse;
import com.rates.spring.rest.dto.response.RateResponse;
import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.service.converter.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateEntityToResponseConverter implements ConverterService<RateEntity, RateResponse> {
    private final ConverterService<RateDictEntity, RateDictResponse> rateDictConverter;
    private final ConverterService<CountryEntity, CountryResponse> countryConverter;
    @Autowired
    public RateEntityToResponseConverter(ConverterService<RateDictEntity, RateDictResponse> rateDictConverter,
                                         ConverterService<CountryEntity, CountryResponse> countryConverter){
        this.countryConverter = countryConverter;
        this.rateDictConverter = rateDictConverter;
    }
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

        response.setCountry(countryConverter.convert(source.getCountry()));
        response.setRateDict(rateDictConverter.convert(source.getRateDict()));

        return response;
    }
}
