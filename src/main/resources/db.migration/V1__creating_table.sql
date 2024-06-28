
    create table countries (
        char_code varchar(3) not null,
        num_code integer not null,
        id uuid not null,
        name varchar(255) not null,
        primary key (id)
    )

    create table rate_dict (
        num_code integer not null,
        id uuid not null,
        char_code varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    )
    create table rate_dict (
        num_code integer not null,
        id uuid not null,
        char_code varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    )

    create table rates (
            value numeric(38,2) not null,
            country_id bigint not null,
            created timestamp(6) not null,
            nominal bigint not null,
            rate_date timestamp(6) not null,
            rate_dict_id bigint not null,
            updated timestamp(6) not null,
            id uuid not null,
            currency_id varchar(255) not null,
            primary key (id)
        )
