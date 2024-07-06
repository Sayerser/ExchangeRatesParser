package com.rates.spring.rest.repository;

import com.rates.spring.rest.entity.RateEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RateRepository extends CommonRepository<RateEntity, UUID> {}
