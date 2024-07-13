package com.rates.spring.rest.repository;

import com.rates.spring.rest.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExchangeRateEntityRepository extends JpaRepository<RateEntity, UUID> {
}
