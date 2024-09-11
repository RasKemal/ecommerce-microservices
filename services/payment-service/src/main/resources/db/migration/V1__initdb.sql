CREATE TABLE IF NOT EXISTS payment (
                         id SERIAL PRIMARY KEY,
                         amount DECIMAL(19, 2) NOT NULL,
                         payment_method VARCHAR(255) NOT NULL,
                         order_id INT NOT NULL,
                         created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
