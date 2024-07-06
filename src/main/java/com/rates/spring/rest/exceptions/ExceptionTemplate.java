package com.rates.spring.rest.exceptions;

/** Шаблон исключения */
public interface ExceptionTemplate {

    /**
     * @return Билдер шаблона исключения
     */
    static ExceptionTemplateBuilder builder() {
        return new ExceptionTemplateBuilder();
    }

    /**
     * @return Статус ответа
     */
    int getResponseStatus();

    /**
     * @return Внутренний код ошибки (3 знака)
     */
    int getErrorCode();

    /**
     * @return Шаблон сообщения (или само сообщение), который будет отправлен пользователю
     */
    String getErrorMessageTemplate();

    /**
     * @return {@code true} - логировать исключение в обработчике ошибок, иначе {@code false}
     */
    boolean isLogException();
}
