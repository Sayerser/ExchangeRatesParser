package com.rates.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private String currencyId;
    private Long countryId;
    private Long rateDictId;
    private LocalDateTime rateDate;
    private Long nominal;
    private BigDecimal value;
}
