package com.rates.spring.rest.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID>, JpaSpecificationExecutor<T> {

    static <T> Specification<T> whereAttributeLike(String attribute, String text) {
        return (root, query, cb) -> {
            String pattern = text.toLowerCase();
            if (pattern.startsWith("*")) {
                pattern = "%" + pattern.substring(1);
            }
            if (pattern.endsWith("*")) {
                pattern = pattern.substring(0, pattern.length() - 1) + "%";
            }

            Path<?> path;
            if (attribute.contains(".")) {
                String[] split = attribute.split("\\.");

                path = root.get(split[0]);
                for (int i = 1; i < split.length; i++) {
                    path = path.get(split[i]);
                }
            } else {
                path = root.get(attribute);
            }

            return cb.like(
                    cb.lower(path.as(String.class)),
                    pattern.toLowerCase()
            );
        };
    }

    Optional<T> findById(ID id);

    List<T> findAll();
}
