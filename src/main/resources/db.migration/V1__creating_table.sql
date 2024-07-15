-- Создание таблицы countries
create table countries (
    char_code varchar(3) not null,
    num_code integer not null,
    id uuid not null,
    name varchar(255) not null,
    primary key (id)
);

-- Создание таблицы rate_dict
create table rate_dict (
    num_code integer not null,
    id uuid not null,
    char_code varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);

-- Создание таблицы rates
create table rates (
    value numeric(38,2) not null,
    country_id uuid not null, -- Исправлено на uuid
    created timestamp(6) not null,
    nominal bigint not null,
    rate_date timestamp(6) not null,
    rate_dict_id uuid not null, -- Исправлено на uuid
    updated timestamp(6),
    id uuid not null,
    currency_id varchar(255) not null,
    primary key (id),
    foreign key (country_id) references countries(id), -- Добавлено ограничение внешнего ключа
    foreign key (rate_dict_id) references rate_dict(id) -- Добавлено ограничение внешнего ключа
);
