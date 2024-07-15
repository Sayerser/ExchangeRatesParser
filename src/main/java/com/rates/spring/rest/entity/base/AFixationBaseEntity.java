package com.rates.spring.rest.entity.base;

import com.rates.spring.rest.domain.base.FixationDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

/**
 * Базовый класс для всех сущностей с типом идентификатора {@link Long}
 * и фиксации даты и времени обновления и создания сущности с типом {@link LocalDateTime}
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AFixationBaseEntity extends ABaseEntity implements FixationDateTime<LocalDateTime> {

    /**
     * Дата создания записи
     */
    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Дата обновления записи
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}
