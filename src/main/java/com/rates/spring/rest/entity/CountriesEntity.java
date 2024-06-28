package com.rates.spring.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "countries")
public class CountriesEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "num_code", nullable = false)
    private Integer numCode;

    @Column(name = "char_code", nullable = false, length = 3)
    private String charCode;
}
