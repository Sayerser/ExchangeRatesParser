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
@XmlRootElement(name = "ExRate")
public class ExchangeRateListXml {
    /**
     * Дата курса
     */
    @XmlAttribute(name = "Date")
    private String Date;

    @XmlElement(name = "Currency")
    private List<CurrencyXml> currencyList;
}
