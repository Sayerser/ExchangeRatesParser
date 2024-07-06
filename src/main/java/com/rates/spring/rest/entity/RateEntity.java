package com.rates.spring.rest.entity;

import com.rates.spring.rest.entity.base.ABaseEntity;
import com.rates.spring.rest.entity.base.AFixationBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Класс сущности: курс валюты */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "rates")
public class RateEntity extends AFixationBaseEntity {

    /** Идентификатор курса валюты */
    @Column(name = "currency_id")
    private String currencyId;

    /** Страна */
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryId;

    /** Курс валюты по словарю */
    @ManyToOne
    @JoinColumn(name = "rate_dict_id")
    private RateDictEntity rateDictId;

    /** Дата курса */
    @Column(name = "rate_date")
    private LocalDateTime rateDate;

    /** Номинал валюты */
    @Column(name = "nominal")
    private Integer nominal;

    /** Значение */
    @Column(name = "value")
    private BigDecimal value;
}