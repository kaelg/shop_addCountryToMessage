--liquibase formatted sql

--changeset liquibase:13

ALTER TABLE message
ADD country varchar(255);