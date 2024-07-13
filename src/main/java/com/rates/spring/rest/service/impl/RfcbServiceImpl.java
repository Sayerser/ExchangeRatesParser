package com.rates.spring.rest.service.impl;

import com.rates.spring.rest.repository.ExchangeRateEntityRepository;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.property.RfcbProperties;
import com.rates.spring.rest.service.RfcbService;
import com.rates.spring.rest.xml.CurrencyXml;
import com.rates.spring.rest.xml.ExchangeRateListXml;
import com.rates.spring.rest.service.converter.impl.CurrencyXmlToRateEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RfcbServiceImpl implements RfcbService {
    private final DateTimeFormatter dateTimeFormatter;

    private final RfcbProperties rfcbProperties;
    private final WebClient webClient;
    private final CurrencyXmlToRateEntityConverter currencyXmlExchangeRateEntityConverter;
    private final ExchangeRateEntityRepository repository;

    @Autowired
    public RfcbServiceImpl(ExchangeRateEntityRepository repository,
                           RfcbProperties rfcbProperties,
                           WebClient webClient,
                           CurrencyXmlToRateEntityConverter currencyXmlExchangeRateEntityConverter){
        this.repository = repository;
        this.rfcbProperties = rfcbProperties;
        this.webClient = webClient;
        this.currencyXmlExchangeRateEntityConverter = currencyXmlExchangeRateEntityConverter;
        dateTimeFormatter = DateTimeFormatter.ofPattern(rfcbProperties.getFormatDate());
    }

    @Async
    @Scheduled(initialDelayString = "${scheduler.init-delay}", fixedRateString = "${scheduler.fixed-rate}", timeUnit = TimeUnit.SECONDS)
    @Override
    public void autoSynchranize(){
        LocalDate now = LocalDate.now();
        log.info("Scheduled task started at {}", LocalDateTime.now());
        try {
            synchronize(now);
            log.info("Synchronization completed successfully at {}", LocalDateTime.now());
        } catch (Exception e) {
            log.error("Error during synchronization", e);
        }
    }

    public void synchronize(LocalDate date) throws Exception {
        log.info("Synchronizing data for date {}", date);
        ExchangeRateListXml exchangeRateListXml = webClient.get()
                .uri(rfcbProperties.getEndpoint() + date.format(dateTimeFormatter))
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(ExchangeRateListXml.class)
                .toFuture()
                .get();

        for (CurrencyXml currencyXml : exchangeRateListXml.getCurrencyList()) {
            RateEntity rateEntity = currencyXmlExchangeRateEntityConverter.convert(currencyXml, exchangeRateListXml);
            repository.save(rateEntity);
            log.debug("Saved rateEntity: {}", rateEntity);
        }
    }
}
