package com.rates.spring.rest.xml;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Класс корневого элемента xml-схемы курса валют
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ValCurs")
public class ExchangeRateListXml {
    /**
     * Дата курса
     */
    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name = "Valute")
    private List<CurrencyXml> currencyList;
}
