CREATE TABLE panic_button (
    id BIGSERIAL PRIMARY KEY,
    time TIME NOT NULL,
    users_id BIGINT,
    CONSTRAINT fk_panic_button_users FOREIGN KEY (users_id) REFERENCES users (id)
);
