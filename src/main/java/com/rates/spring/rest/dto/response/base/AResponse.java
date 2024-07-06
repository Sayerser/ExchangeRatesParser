package com.rates.spring.rest.dto.response.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Стандартизированный объект ответа сервиса */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Стандартизированный объект ответа сервиса")
public abstract class AResponse {

    /** HTTP-статус ответа (должен совпадать с возвращаемым статусом) */
    private int status;
}

