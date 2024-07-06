package com.rates.spring.rest.entity.base;

import com.rates.spring.rest.domain.base.Identifiable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    protected UUID id;

}
