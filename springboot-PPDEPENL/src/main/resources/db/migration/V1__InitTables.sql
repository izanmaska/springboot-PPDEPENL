CREATE TABLE if not exists users (
    id BIGSERIAL PRIMARY KEY,
    username varchar(255) NOT NULL,
    unique (username)
);

CREATE TABLE if not exists communities (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES users(id),
    unique (name)
);