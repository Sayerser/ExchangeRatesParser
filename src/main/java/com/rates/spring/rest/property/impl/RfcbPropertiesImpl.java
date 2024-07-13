package com.rates.spring.rest.property.impl;

import com.rates.spring.rest.property.RfcbProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rfcb")
public class RfcbPropertiesImpl implements RfcbProperties {

    /**
     * URL сервиса Центрального банка
     */
    private String url;

    /**
     * endpoint запроса получение курса валют по искомой дате
     */
    private String endpoint;

    /**
     * формат даты
     */
    private String formatDate;


    /**
     * таймаут подключения
     */
    private Integer connectionTimeout;

    /**
     * таймаут ожидания ответа
     */
    private Integer responseTimeout;

    /**
     * максимальное количество повторных подключений
     */
    private Integer maxConnections;
}
