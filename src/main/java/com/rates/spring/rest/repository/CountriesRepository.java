package com.rates.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rates.spring.rest.entity.CountriesEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CountriesRepository extends JpaRepository<CountriesEntity, UUID>, JpaSpecificationExecutor<CountriesEntity> {
}

