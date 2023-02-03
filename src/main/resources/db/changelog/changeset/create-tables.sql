--liquibase formatted sql
--changeset nikitalipatov:create-tables

create sequence houses_id_seq start with 1 increment by 1;
create sequence users_id_seq start with 1 increment by 1;

create table houses (
    id integer not null,
    adress varchar(255),
    owner_id integer,
    primary key (id)
);

create table houses_residents (
    houses_id integer not null,
    residents_id integer not null,
    primary key (houses_id, residents_id)
);

create table users (
    id integer not null,
    age integer not null,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id)
);

alter table if exists houses_residents
    add constraint UK_3ynk2cqn1lj33d5kx54tu989j unique (residents_id);

alter table if exists houses
    add constraint FKn9yr5cdkajfhcbtrwnvq8seyl foreign key (owner_id) references users;

alter table if exists houses_residents
    add constraint FKkxt7rdk0sy7s40630v9y0h7cb foreign key (residents_id) references users;

alter table if exists houses_residents add constraint FKcd8an3gbl9jio3n32nsu88nt7 foreign key (houses_id) references houses


