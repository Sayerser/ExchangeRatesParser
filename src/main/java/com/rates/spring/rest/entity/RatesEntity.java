package com.rates.spring.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.util.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "rates")
public class RatesEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "currency_id", nullable = false)
    private String currencyId;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "rate_dict_id", nullable = false)
    private Long rateDictId;

    @Column(name = "rate_date", nullable = false)
    private LocalDateTime rateDate;

    @Column(name = "nominal", nullable = false)
    private Long nominal;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
}
