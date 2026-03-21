--liquibase formatted sql

--changeset UserN:1
--comment first
create table users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(100),
    password VARCHAR(30)
);

create table posts
(
    id SERIAL PRIMARY KEY,
    owner INT REFERENCES users(id),
    head VARCHAR(100),
    period TIMESTAMP,
    text text
);

create table images
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    ext VARCHAR(10),
    image VARCHAR(100)
);

create table images_post
(
    id SERIAL PRIMARY KEY,
    post_id INT REFERENCES posts(id),
    image_id INT REFERENCES images(id)
);

create table requests
(
    id SERIAL PRIMARY KEY,
    from_id INT REFERENCES users(id),
    friend INT REFERENCES users(id),
    status VARCHAR(10)
);

create table subscriptions
(
    id SERIAL PRIMARY KEY,
    owner INT REFERENCES users(id),
    subscriber INT REFERENCES users(id)
);

create table messages
(
    id SERIAL PRIMARY KEY,
    from_id INT REFERENCES users(id),
    to_id INT REFERENCES users(id),
    text text
);


