package com.rates.spring.rest.domain.base;

/**
 * Интерфейс фиксации даты и времени сущности
 * @param <DT> - тип даты и времени создания/обновления сущности
 */
public interface FixationDateTime<DT>  {
    DT getCreated();
    void setCreated(DT dateTime);
    DT getUpdated();
    void setUpdated(DT dateTime);
}
