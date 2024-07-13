package com.rates.spring.rest.property;

public interface RfcbProperties {

    /**
     * @return URL сервиса Центрального банка
     */
    String getUrl();

    /**
     * @return endpoint запроса получение курса валют по искомой дате
     */
    String getEndpoint();

    /**
     * @return формат даты
     */
    String getFormatDate();


    /**
     * @return таймаут подключения
     */
    Integer getConnectionTimeout();

    /**
     * @return таймаут ожидания ответа
     */
    Integer getResponseTimeout();

    /**
     * @return максимальное количество повторных подключений
     */
    Integer getMaxConnections();
}
