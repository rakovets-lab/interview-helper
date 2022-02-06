DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id    UUID        not null primary key,
    username   varchar(32) not null unique,
    password   varchar(32) not null,
    role       varchar(16) not null,
    email      varchar(32) not null unique,
    first_name varchar(32) not null,
    last_name  varchar(32) not null
);

INSERT INTO users (user_id, username, password, role, email, first_name, last_name)
VALUES ('c8bd74bf-c963-43e3-b103-56c98817d33a', 'admin', '12345', 'ADMIN', 'admin@rakovets.by', 'Dmitry', 'Rakovets');
