package com.rates.spring.rest.exceptions;
/**
 * Шаблоны ошибок
 */
public class BaseExceptionTemplates {
    public static String placeholder(String param) {
        return "${" + param + "}";
    }

    /**
     * Неизвестная ошибка
     * <ul>
     *     <li/>Код ответа: {@code 500}
     *     <li/>Внутренний код ошибки: {@code 0}
     *     <li/>Логировать исключение: {@code true}
     *     <li/>Шаблон сообщения: {@code Unknown Error}
     * </ul>
     */
    public static final ExceptionTemplate UnknownError = ExceptionTemplate.builder()
            .responseStatus(500)
            .errorCode(0)
            .errorMessageTemplate("Unknown Error")
            .logException(true)
            .build();

    /**
     * Ресурс не найден
     * <ul>
     *     <li/>Код ответа: {@code 404}
     *     <li/>Внутренний код ошибки: {@code 1}
     *     <li/>Логировать исключение: {@code false}
     *     <li/>Шаблон сообщения: {@code Item not found}
     * </ul>
     */
    public static final ExceptionTemplate ItemNotFound = ExceptionTemplate.builder()
            .responseStatus(404)
            .errorCode(1)
            .errorMessageTemplate("Item not found")
            .logException(false)
            .build();


    /**
     * Шаблоны ошибок валидации
     */
    public static class ValidationTemplates {

        /**
         * Ошибка валидации
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 100}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Request validation error}
         * </ul>
         */
        public static final ExceptionTemplate ValidationError = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(100)
                .errorMessageTemplate("Request validation error")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Обязательное поле или значение в этом поле не передано
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 101}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Required field is empty}
         * </ul>
         */
        public static final ExceptionTemplate RequiredFieldIsNullOrNotPresent = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(101)
                .errorMessageTemplate("Missing required field")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Значение не может быть пустым
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 102}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Value cannot be empty}
         * </ul>
         */
        public static final ExceptionTemplate FieldValueIsEmpty = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(102)
                .errorMessageTemplate("Value cannot be empty")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Неверный тип значения
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 103}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value type}
         * </ul>
         */
        public static final ExceptionTemplate InvalidValueType = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(103)
                .errorMessageTemplate("Invalid value type")
                .logException(false)
                .build();

        /**
         * Ошибка валидации. Неверное значение
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 104}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value}
         * </ul>
         */
        public static final ExceptionTemplate InvalidValue = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(104)
                .errorMessageTemplate("Invalid value")
                .logException(false)
                .build();
    }


    /**
     * Шаблоны ошибок валидации словарей
     */
    public static class DictValidationTemplates {
        /**
         * Ошибка валидации. Объект словаря пустой
         * <ul>
         *     <li/>Код ответа: {@code 400}
         *     <li/>Внутренний код ошибки: {@code 150}
         *     <li/>Логировать исключение: {@code false}
         *     <li/>Шаблон сообщения: {@code Invalid value}
         * </ul>
         */
        public static final ExceptionTemplate EmptyDictEntity = ExceptionTemplate.builder()
                .responseStatus(400)
                .errorCode(150)
                .errorMessageTemplate("Объект словаря пустой")
                .logException(false)
                .build();
    }

}
