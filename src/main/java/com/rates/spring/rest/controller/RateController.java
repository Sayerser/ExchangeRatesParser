package com.rates.spring.rest.controller;

import com.rates.spring.rest.dto.response.RateResponse;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.service.RateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/rates")
@Api(value = "Rate Management System")
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get rate by ID")
    public ResponseEntity<RateResponse> getRateById(@PathVariable UUID id) {
        RateEntity rateEntity = rateService.getRateById(id);
        RateResponse rateResponse = convertToResponse(rateEntity);
        return ResponseEntity.ok(rateResponse);
    }

    private RateResponse convertToResponse(RateEntity rateEntity) {
        RateResponse rateResponse = new RateResponse();
        rateResponse.setId(rateEntity.getId());
        rateResponse.setCurrencyId(rateEntity.getCurrencyId());
        rateResponse.setRateDate(rateEntity.getRateDate());
        rateResponse.setNominal(rateEntity.getNominal());
        rateResponse.setValue(rateEntity.getValue());
        return rateResponse;
    }
}

