CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    employee_type VARCHAR(50),
    phone_number BIGINT NOT NULL,
    adress VARCHAR(255) NOT NULL,
    cep BIGINT NOT NULL
);
