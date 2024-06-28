package com.rates.spring.rest.repository;

import com.rates.spring.rest.entity.RateDictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RateDictRepository extends JpaRepository<RateDictEntity, UUID>, JpaSpecificationExecutor<RateDictEntity> {
}
