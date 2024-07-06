package com.rates.spring.rest.service;
import com.rates.spring.rest.dto.CountryDTO;
import com.rates.spring.rest.entity.CountryEntity;
import java.util.List;
import java.util.UUID;

public interface CountryService {
    CountryEntity create(CountryDTO dto);

    List<CountryEntity> readAll();

    CountryEntity update(CountryEntity countries);

    void delete(UUID id);
}
