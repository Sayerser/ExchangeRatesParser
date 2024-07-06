package com.rates.spring.rest.repository;

import java.io.Serializable;
import com.rates.spring.rest.domain.base.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T extends Identifiable<TID>, TID extends Comparable<TID> & Serializable> extends BaseRepository<T, TID>,
        JpaRepository<T, TID> {

}
