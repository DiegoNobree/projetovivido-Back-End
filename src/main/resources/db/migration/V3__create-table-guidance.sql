CREATE TABLE guidance (
    id BIGSERIAL PRIMARY KEY,
    users_id BIGINT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    timestamp TIME NOT NULL,
    user_funcionario_id BIGINT,
    type VARCHAR(50),
    CONSTRAINT fk_guidance_users FOREIGN KEY (users_id) REFERENCES users (id),
    CONSTRAINT fk_guidance_user_funcionario FOREIGN KEY (user_funcionario_id) REFERENCES users (id)
);
