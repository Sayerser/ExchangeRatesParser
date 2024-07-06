package com.rates.spring.rest.service.Impl;

import com.rates.spring.rest.dto.CountryDTO;
import com.rates.spring.rest.entity.CountryEntity;
import com.rates.spring.rest.repository.CountryRepository;
import com.rates.spring.rest.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryEntity create(CountryDTO dto){
        CountryEntity countries = CountryEntity.builder()
                .name(dto.getName())
                .numCode(dto.getNumCode())
                .charCode(dto.getCharCode())
                .build();
        return countryRepository.save(countries);
    }

    //чтение всех стран из БД
    public List<CountryEntity> readAll(){
        return countryRepository.findAll();
    }

    public CountryEntity update(CountryEntity countries){
        return countryRepository.save(countries);
    }

    public void delete(UUID id){
        countryRepository.deleteById(id);
    }
}
