package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.xml.CurrencyXml;
import com.rates.spring.rest.xml.ExchangeRateListXml;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CurrencyXmlToRateEntityConverter {

    @PersistenceContext
    private EntityManager entityManager;

    public RateEntity convert(CurrencyXml currencyXml, ExchangeRateListXml exchangeRateListXml) {
        RateEntity rateEntity = new RateEntity();

        // Пример получения страны по коду
        CountryEntity countryEntity = entityManager
                .createQuery("SELECT c FROM CountryEntity c WHERE c.charCode = :charCode", CountryEntity.class)
                .setParameter("charCode", currencyXml.getCharCode())
                .getSingleResult();

        // Пример получения словаря курса по коду
        RateDictEntity rateDictEntity = entityManager
                .createQuery("SELECT r FROM RateDictEntity r WHERE r.charCode = :charCode", RateDictEntity.class)
                .setParameter("charCode", currencyXml.getCharCode())
                .getSingleResult();

        rateEntity.setCountryId(countryEntity);
        rateEntity.setRateDictId(rateDictEntity);
        rateEntity.setCurrencyId(currencyXml.getId());
        rateEntity.setNominal(Integer.parseInt(currencyXml.getNominal()));
        rateEntity.setValue(new BigDecimal(currencyXml.getValue()));
        // Установка даты курса из ExchangeRateListXml
        LocalDateTime rateDate = LocalDateTime.parse(exchangeRateListXml.getDate());
        rateEntity.setRateDate(rateDate);

        return rateEntity;
    }
}
