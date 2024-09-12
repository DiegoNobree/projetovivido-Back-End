CREATE TABLE guardians (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number BIGINT NOT NULL,
    CONSTRAINT fk_user_guardian
        FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);
