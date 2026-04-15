--liquibase formatted sql

--changeset UserN:1
--comment first
create table if not exists users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(100),
    password text
);

create table if not exists posts
(
    id SERIAL PRIMARY KEY,
    owner INT REFERENCES users(id),
    head VARCHAR(100),
    period TIMESTAMP,
    text text
);

create table if not exists images
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    ext VARCHAR(10),
    image VARCHAR(100),
    post_id INT REFERENCES posts(id)
);

create table if not exists requests
(
    id SERIAL PRIMARY KEY,
    from_id INT REFERENCES users(id),
    friend INT REFERENCES users(id),
    status VARCHAR(10)
);

create table if not exists subscriptions
(
    id SERIAL PRIMARY KEY,
    owner INT REFERENCES users(id),
    subscriber INT REFERENCES users(id)
);

create table if not exists messages
(
    id SERIAL PRIMARY KEY,
    from_id INT REFERENCES users(id),
    to_id INT REFERENCES users(id),
    text text
);


