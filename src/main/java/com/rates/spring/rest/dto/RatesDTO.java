package com.rates.spring.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RatesDTO {
    private String currencyId;
    private Long countryId;
    private Long rateDictId;
    private LocalDateTime rateDate;
    private Long nominal;
    private BigDecimal value;
    private LocalDateTime created;
    private LocalDateTime updated;
}
