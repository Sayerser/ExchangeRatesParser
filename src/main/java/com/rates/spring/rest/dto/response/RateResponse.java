package com.rates.spring.rest.dto.response;

import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.entity.base.AFixationBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Класс ответа сервиса: курс валюты */
@Getter
@Setter
@NoArgsConstructor
@Schema(title = "Класс ответа сервиса: курс валюты")
public class RateResponse {

    /** Идентификатор записи */
    @Schema(description = "Идентификатор записи")
    private UUID id;

    /** Идентификатор курса валюты */
    @Schema(description = "Идентификатор курса валюты")
    private String currencyId;

    /** Страна */
    @Schema(description =  "Страна")
    private CountryResponse country;

    /** Курс валюты по словарю */
    @Schema(description =  "Курс валюты по словарю")
    private RateDictResponse rateDict;

    /** Дата курса */
    @Schema(description = "Дата курса")
    private LocalDateTime rateDate;

    /** Номинал валюты */
    @Schema(description = "Номинал валюты")
    private Integer nominal;

    /** Значение */
    @Schema(description = "Значение")
    private BigDecimal value;
}
