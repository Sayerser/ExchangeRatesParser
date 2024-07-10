package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.dto.response.base.ErrorResponse;
import com.rates.spring.rest.exceptions.ServiceException;
import com.rates.spring.rest.service.converter.ConverterService;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceToErrorResponseImpl implements ConverterService<ServiceException, ErrorResponse> {
    @Override
    public ErrorResponse convert(ServiceException source) {
        if (source == null) {
            return null;
        }

        ErrorResponse destination = new ErrorResponse();
        destination.setStatus(source.getStatus());
        destination.setCode(source.getErrorCode());
        destination.setMessage(source.getErrorMessage());
        destination.setData(source.getPayload());

        return destination;
    }
}
