CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    reference VARCHAR(255),
    amount NUMERIC(19, 2),
    payment_method VARCHAR(255),
    costumer_id VARCHAR(255),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE customer_order
    ADD CONSTRAINT chk_payment_method
        CHECK (payment_method IN ('VISA', 'MASTER_CARD', 'DEPOSIT_CARD', 'PAYPAL'));

CREATE TABLE IF NOT EXISTS order_line (
        id SERIAL PRIMARY KEY,
        order_id INTEGER REFERENCES customer_order(id) ON DELETE CASCADE,
        product_id INTEGER,
        quantity DOUBLE PRECISION
);