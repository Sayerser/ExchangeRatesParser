package com.rates.spring.rest.service.Impl;

import com.rates.spring.rest.dto.response.RateResponse;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.exceptions.BaseExceptionTemplates;
import com.rates.spring.rest.exceptions.ServiceException;
import com.rates.spring.rest.repository.RateRepository;
import com.rates.spring.rest.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository repository;

    @Autowired
    public RateServiceImpl(RateRepository repository) {
        this.repository = repository;
    }

    @Override
    public RateEntity getRateById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                ServiceException.fromTemplate(BaseExceptionTemplates.ItemNotFound).build());
    }
}

