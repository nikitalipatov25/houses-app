--liquibase formatted sql
--changeset nikitalipatov:insert-tables

insert into users values (1, 22, 'abc@gmail.com', 'Ivan Ivanov', '$2a$10$0aW.keqk5rv0JH7EB//QReGSy.fmj51L6jArWmx0LAiRzMzIrL2J.', 'USER');
insert into users values (2, 47, '123@gmail.com', 'Petr Petrov', '$$2a$10$GRsxGIs7cKJqfJSzU5ytyOAKhybvi3EECfiQhhdAZ2xiOKlvatpia', 'USER');
insert into users values (3, 68, 'password@gmail.com', 'Alex Alexandrov', '$2a$10$kjcmOHDgI0UQ9NI4J2XopecPHIOjV6nIo/fGvEcn/s8xAu4dAmdbi', 'USER');

insert into houses values (1, 'Bourbon Street, 4');
insert into houses values (2, 'Broadway, 21A');
