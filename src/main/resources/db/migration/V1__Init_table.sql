CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    costumer_id BIGINT,
    total_price DECIMAL,
    status VARCHAR,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    product_id BIGINT,
    quantity INT,
    price DECIMAL
);