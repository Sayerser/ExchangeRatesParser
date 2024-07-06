package com.rates.spring.rest.dto.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса, содержащий информацию об ошибке")
public class ErrorResponse extends AResponse {

    /**
     * Код ошибки {@code xxxyyy}, где {@code xxx} - код сервиса, {@code yyy} - внутренний код ошибки
     */
    @Schema(description = "Код ошибки xxxyyy, где xxx - код сервиса, yyy - внутренний код ошибки")
    private int code;

    /** Описание ошибки */
    @Schema(description = "Описание ошибки")
    private String message = "";

    /** Детали ошибки */
    @Schema(description = "Детали ошибки")
    private Data data;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Объект с деталями ошибки")
    public static class Data {

        /** Идентификатор сущности, работа с которым привела к ошибке */
        @Schema(description = "Идентификатор сущности, работа с которым привела к ошибке")
        private Object id;

        /** Имя поля, которое не прошло валидацию */
        @Schema(description = "Имя поля, которое не прошло валидацию")
        private String fieldName;

        /** Значение поля, которое не прошло валидацию */
        @Schema(description = "Значение поля, которое не прошло валидацию")
        private Object fieldValue;

        /** Сообщение, чем вызвано исключение */
        @Schema(description = "Сообщение, чем вызвано исключение")
        private String causedBy;
    }
}

