package com.rates.spring.rest.service.Impl;

import com.rates.spring.rest.dto.RateDTO;
import com.rates.spring.rest.entity.RateEntity;
import com.rates.spring.rest.repository.RateRepository;
import com.rates.spring.rest.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;

    public RateEntity create(RateDTO dto){
        RateEntity ratesDict = RateEntity.builder()
                .currencyId(dto.getCurrencyId())
                .countryId(dto.getCountryId())
                .rateDictId(dto.getRateDictId())
                .rateDate(dto.getRateDate())
                .nominal(dto.getNominal())
                .value(dto.getValue())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .build();
        return rateRepository.save(ratesDict);
    }

    //чтение всех стран из БД
    public List<RateEntity> readAll(){
        return rateRepository.findAll();
    }

    public RateEntity update(RateEntity rates){
        return rateRepository.save(rates);
    }

    public void delete(UUID id){
        rateRepository.deleteById(id);
    }
}
