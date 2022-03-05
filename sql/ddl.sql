drop table if exists todo CASCADE;
create table todo
(
    id   bigint generated by default as identity,
    title varchar(30) not null,
    memo varchar(256),
    date date not null,
    done int default 0 not null,
    primary key (id)
);