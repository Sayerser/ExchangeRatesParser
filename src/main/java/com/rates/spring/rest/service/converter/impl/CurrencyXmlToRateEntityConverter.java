package com.rates.spring.rest.service.converter.impl;

import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.repository.CountryRepository;
import com.rates.spring.rest.repository.RateDictRepository;
import com.rates.spring.rest.xml.CurrencyXml;
import com.rates.spring.rest.xml.ExchangeRateListXml;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
@Component
public class CurrencyXmlToRateEntityConverter {
    private final RateDictRepository rateDictRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CurrencyXmlToRateEntityConverter(RateDictRepository rateDictRepository,
                                            CountryRepository countryRepository) {
        this.rateDictRepository = rateDictRepository;
        this.countryRepository = countryRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public RateEntity convert(CurrencyXml currencyXml, ExchangeRateListXml exchangeRateListXml, DateTimeFormatter dateTimeFormatter) {
        RateEntity rateEntity = new RateEntity();

        var countryEntity = countryRepository.findByCharCode(currencyXml.getCharCode().describeConstable().orElse(null)).orElse(null);
        if (countryEntity == null) {
            countryEntity = newCountry(currencyXml);
        }

        var rateDictEntity = rateDictRepository.findByCharCode(currencyXml.getCharCode().describeConstable().orElse(null)).orElse(null);
        if (rateDictEntity == null) {
            rateDictEntity = newRateDict(currencyXml);
        }

        rateEntity.setCurrencyId(currencyXml.getId());
        rateEntity.setNominal(Integer.parseInt(currencyXml.getNominal()));
        rateEntity.setValue(new BigDecimal(currencyXml.getValue().replace(",", "."))); // Заменяем запятую на точку

        // Изменение для обработки даты
        LocalDate rateDate = LocalDate.parse(exchangeRateListXml.getDate(), dateTimeFormatter);
        LocalDateTime rateDateTime = rateDate.atTime(LocalTime.MIDNIGHT); // Добавляем время, например, полночь
        rateEntity.setRateDate(rateDateTime);

        // Устанавливаем связи
        rateEntity.setCountry(countryEntity);
        rateEntity.setRateDict(rateDictEntity);

        return rateEntity;
    }

    private static @NotNull String getVal(CurrencyXml currencyXml) {
        return currencyXml.getValue().replace(",", ".");
    }

    private RateDictEntity newRateDict(CurrencyXml currencyXml) {
        final var entity = new RateDictEntity();
        entity.setCharCode(currencyXml.getCharCode());
        entity.setNumCode(Integer.valueOf(currencyXml.getNumCode()));
        entity.setName(currencyXml.getName());
        return rateDictRepository.save(entity);
    }

    private CountryEntity newCountry(CurrencyXml currencyXml) {
        final var entity = new CountryEntity();
        entity.setCharCode(currencyXml.getCharCode());
        entity.setNumCode(Integer.valueOf(currencyXml.getNumCode()));
        entity.setName(currencyXml.getName());
        return countryRepository.save(entity);
    }
}

