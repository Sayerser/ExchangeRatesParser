package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.dto.response.RateDictResponse;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.service.converter.ConverterService;
import org.springframework.stereotype.Component;

@Component
public class RateDictEntityToResponseConverter implements ConverterService<RateDictEntity, RateDictResponse> {
    @Override
    public RateDictResponse convert(RateDictEntity source) {
        if (source == null) {
            return null;
        }
        RateDictResponse response = new RateDictResponse();
        response.setId(source.getId());
        response.setCharCode(source.getCharCode());
        response.setNumCode(source.getNumCode());
        response.setName(source.getName());
        return response;
    }
}
