package com.rates.spring.rest.service;

import com.rates.spring.rest.dto.RatesDTO;
import com.rates.spring.rest.entity.RatesEntity;
import com.rates.spring.rest.repository.RatesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RatesService {
    private final RatesRepository ratesRepository;

    public RatesEntity create(RatesDTO dto){
        RatesEntity ratesDict = RatesEntity.builder()
                .currencyId(dto.getCurrencyId())
                .countryId(dto.getCountryId())
                .rateDictId(dto.getRateDictId())
                .rateDate(dto.getRateDate())
                .nominal(dto.getNominal())
                .value(dto.getValue())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .build();
        return ratesRepository.save(ratesDict);
    }

    //чтение всех стран из БД
    public List<RatesEntity> readAll(){
        return ratesRepository.findAll();
    }

    public RatesEntity update(RatesEntity rates){
        return ratesRepository.save(rates);
    }

    public void delete(UUID id){
        ratesRepository.deleteById(id);
    }
}
