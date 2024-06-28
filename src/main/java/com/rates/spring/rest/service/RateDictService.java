package com.rates.spring.rest.service;

import com.rates.spring.rest.dto.RateDictDTO;
import com.rates.spring.rest.entity.RateDictEntity;
import com.rates.spring.rest.repository.RateDictRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RateDictService {
    private final RateDictRepository rateDictRepository;

    public RateDictEntity create(RateDictDTO dto){
        RateDictEntity rateDict = RateDictEntity.builder()
                .name(dto.getName())
                .numCode(dto.getNumCode())
                .charCode(dto.getCharCode())
                .build();
        return rateDictRepository.save(rateDict);
    }

    //чтение всех стран из БД
    public List<RateDictEntity> readAll(){
        return rateDictRepository.findAll();
    }

    public RateDictEntity update(RateDictEntity rateDict){
        return rateDictRepository.save(rateDict);
    }

    public void delete(UUID id){
        rateDictRepository.deleteById(id);
    }
}
