CREATE TABLE feedback (
    id BIGSERIAL PRIMARY KEY,
    users_id BIGINT,
    feedback VARCHAR(255) NOT NULL,
    time TIME NOT NULL,
    CONSTRAINT fk_feedback_users FOREIGN KEY (users_id) REFERENCES users (id)
);
