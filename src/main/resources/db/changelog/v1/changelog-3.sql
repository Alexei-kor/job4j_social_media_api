--liquibase formatted sql

--changeset UserN:3
--comment first
CREATE TABLE if not exists  roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE if not exists  users_roles (
    role_id INT REFERENCES roles(id),
    user_id INT REFERENCES users(id)
);


