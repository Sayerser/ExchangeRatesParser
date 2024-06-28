package com.rates.spring.rest.service;

import com.rates.spring.rest.dto.CountriesDTO;
import com.rates.spring.rest.entity.CountriesEntity;
import com.rates.spring.rest.repository.CountriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CountriesService {
    private final CountriesRepository countriesRepository;

    public CountriesEntity create(CountriesDTO dto){
        CountriesEntity countries = CountriesEntity.builder()
                .name(dto.getName())
                .numCode(dto.getNumCode())
                .charCode(dto.getCharCode())
                .build();
        return countriesRepository.save(countries);
    }

    //чтение всех стран из БД
    public List<CountriesEntity> readAll(){
        return countriesRepository.findAll();
    }

    public CountriesEntity update(CountriesEntity countries){
        return countriesRepository.save(countries);
    }

    public void delete(UUID id){
        countriesRepository.deleteById(id);
    }
}
