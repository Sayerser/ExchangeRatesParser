package com.rates.spring.rest.repository;

import com.rates.spring.rest.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, UUID>, JpaSpecificationExecutor<RateEntity> {
}
