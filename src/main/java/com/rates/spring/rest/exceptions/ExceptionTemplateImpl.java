package com.rates.spring.rest.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Простая реализация шаблона ошибок */
@Getter
@Setter
@NoArgsConstructor
public class ExceptionTemplateImpl implements ExceptionTemplate {

    /** HTTP-статус ответа сервиса */
    private int responseStatus = 500;

    /** Внутренний код ошибки */
    private int errorCode = 0;

    /** Шаблон ошибки */
    private String errorMessageTemplate = "Unknown error";

    /** Флаг логирования исключения в обработчике */
    private boolean logException = true;
}
