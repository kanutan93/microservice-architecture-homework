CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL        NOT NULL,
    username   VARCHAR(50)      NOT NULL,
    firstname  VARCHAR(50)      NOT NULL,
    lastname   VARCHAR(50)      NOT NULL,
    email      VARCHAR(50)      NOT NULL,
    phone      VARCHAR(50)      NOT NULL,

    PRIMARY KEY (id),
    UNIQUE  (email)
);