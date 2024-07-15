package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.dto.response.CountryResponse;
import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.service.converter.ConverterService;
import org.springframework.stereotype.Component;

@Component
public class CountryToResponseConverter implements ConverterService<CountryEntity, CountryResponse> {
    @Override
    public CountryResponse convert(CountryEntity source) {
        if(source == null) {
            return null;
        }

        CountryResponse response = new CountryResponse();
        response.setId(source.getId());
        response.setCharCode(source.getCharCode());
        response.setNumCode(source.getNumCode());
        response.setName(source.getName());
        return response;
    }
}
