package com.rates.spring.rest.service;

public interface ConverterService<S, D> {
    D convert(S source);
}
