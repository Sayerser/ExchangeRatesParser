package com.rates.spring.rest.xml;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Класс элемента объекта валюты xml-схемы курса валют
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyXml {

    /**
     * Идентификатор валюты ЦБ
     */
    @XmlAttribute(name = "ID")
    private String id;

    /**
     * Числовой код валюты
     */
    @XmlElement(name = "NumCode")
    private String numCode;

    /**
     * Строковый код валюты
     */
    @XmlElement(name = "CharCode")
    private String charCode;

    /**
     * Строковое наименование валюты
     */
    @XmlElement(name = "Name")
    private String name;

    /**
     * Единица валюты
     */
    @XmlElement(name = "Nominal")
    private String nominal;

    /**
     * Курс валюты
     */
    @XmlElement(name = "Value")
    private String value;
}
