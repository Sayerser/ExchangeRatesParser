package com.rates.spring.rest.entity;

import com.rates.spring.rest.entity.base.ABaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

/** Класс сущности: страна */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(
        name = "countries",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"num_code", "char_code"})})
public class CountryEntity extends ABaseEntity {

    /** Наименование страны */
    @Column(name = "name", nullable = false)
    private String name;

    /** Числовой код страны */
    @Column(name = "num_code", nullable = false)
    private Integer numCode;

    /** Символьный код страны */
    @Column(name = "char_code", nullable = false)
    private String charCode;
}

