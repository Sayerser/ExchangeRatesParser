package com.rates.spring.rest.service.converter;

public interface ConverterService<S, D> {
    D convert(S source);
}
