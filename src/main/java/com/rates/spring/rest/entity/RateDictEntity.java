package com.rates.spring.rest.entity;

import com.rates.spring.rest.entity.base.ABaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

/** Класс сущности: словарь курса валюты */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(
        name = "rate_dict",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"num_code", "char_code"})})
public class RateDictEntity extends ABaseEntity {

    /** Наименование валюты */
    @Column(name = "name", nullable = false)
    private String name;

    /** Числовой код валюты */
    @Column(name = "num_code", nullable = false)
    private Integer numCode;

    /** Символьный код валюты */
    @Column(name = "char_code", nullable = false)
    private String charCode;
}

