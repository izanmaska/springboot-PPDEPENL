CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    unique (username)
);

CREATE TABLE communities (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES users(id),
    unique (name)
);