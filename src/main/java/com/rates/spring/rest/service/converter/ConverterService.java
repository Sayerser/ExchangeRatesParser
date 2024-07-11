package com.rates.spring.rest.service.converter;

public interface ConverterService<S, D> {

    /**
     * @param source
     * @return
     */
    D convert(S source);
}
