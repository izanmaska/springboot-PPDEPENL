CREATE TABLE if not exists users (
    id BIGSERIAL PRIMARY KEY,
    user_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    user_type varchar(255) NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    url varchar(255),
    unique (user_name)
);

CREATE TABLE if not exists communities (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    owner_id BIGINT,
    owner_username varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    url varchar(255),
    unique (name),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE if not exists transactions (
    id BIGSERIAL PRIMARY KEY,
    user1_id BIGINT,
    user1_type varchar(255),
    user2_id BIGINT,
    user2_type varchar(255),
    date timestamp NOT NULL,
    url varchar(255)
);

CREATE TABLE IF NOT EXISTS community_users (
    community_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY (community_id, user_id),
    FOREIGN KEY (community_id) REFERENCES communities(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);