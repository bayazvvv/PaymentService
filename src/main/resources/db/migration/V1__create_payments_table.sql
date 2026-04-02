CREATE TABLE IF NOT EXISTS payments (
    payment_id BIGSERIAL PRIMARY KEY,
    amount BIGINT NOT NULL CHECK (amount > 0),
    currency VARCHAR(3) NOT NULL CHECK (currency IN ('KZT', 'USD', 'EUR', 'RUB', 'CNY')),
    description VARCHAR(255) NOT NULL,
    client_id BIGINT NOT NULL CHECK (client_id > 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELED'))
);

CREATE INDEX IF NOT EXISTS idx_payments_client_id
    ON payments (client_id);
