--liquibase formatted sql

--changeset UserN:1
--comment first
ALTER TABLE users ADD CONSTRAINT useremail_unique UNIQUE (email);

