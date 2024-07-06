package com.rates.spring.rest.domain.base;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Базовый интерфейс сущности
 *
 * @param <TId> тип идентификатора сущности
 * @implNote Идентификатор должен реализовывать интерфейсы:
 * <ul>
 *  <li/> {@link Comparable Comparable&lt;TId&gt;} - для дальнейшей возможной сортировки
 *  <li/> {@link Serializable} - для сохранения в базу данных
 * </ul>
 */
@JsonPropertyOrder("id")
public interface Identifiable<TId extends Comparable<TId> & Serializable> extends Serializable {

    static <TId extends Comparable<TId> & Serializable, T extends Identifiable<TId>> Comparator<T> comparator() {
        return Comparator.comparing(Identifiable::getId);
    }

    TId getId();

    void setId(TId id);
}

