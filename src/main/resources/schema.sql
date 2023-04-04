CREATE TABLE employee (
    uuid UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    full_name VARCHAR(255),
    birthday DATE,
    hobbies VARCHAR(255)
);