package com.rates.spring.rest.entity.base;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;
import com.rates.spring.rest.domain.base.Identifiable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Базовый класс для всех сущностей с типом идентификатора {@link UUID}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class ABaseEntity implements Identifiable<UUID> {

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    protected UUID id;
}
