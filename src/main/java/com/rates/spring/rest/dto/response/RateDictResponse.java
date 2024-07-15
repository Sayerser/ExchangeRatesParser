package com.rates.spring.rest.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class RateDictResponse implements Serializable {
    private UUID id;
    private String name;
    private Integer numCode;
    private String charCode;
}
