create table manufacturers
(
    id         bigint auto_increment
        primary key,
    name       varchar(45) not null,
    country    varchar(45) not null,
    is_deleted tinyint     NOT NULL DEFAULT 0
)
    comment 'List of car makers.';