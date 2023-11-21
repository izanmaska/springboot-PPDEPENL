CREATE TABLE if not exists users (
    id UUID PRIMARY KEY,
    user_name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    user_type varchar(255),
    date_created timestamp NOT NULL,
    date_updated timestamp,
    unique (user_name)
);

CREATE TABLE if not exists communities (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    owner_id UUID,
    owner_username varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    date_created timestamp NOT NULL,
    date_updated timestamp,
    unique (name),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE if not exists transactions (
    id UUID PRIMARY KEY,
    user1_id UUID,
    user1_type SMALLINT,
    user2_id UUID,
    user2_type SMALLINT,
    date timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS community_users (
    community_id UUID,
    user_id UUID,
    PRIMARY KEY (community_id, user_id),
    FOREIGN KEY (community_id) REFERENCES communities(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE if not exists offering (
    id UUID PRIMARY KEY,
    price DECIMAL NOT NULL,
    currency VARCHAR(255) NOT NULL,
);