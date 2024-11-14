CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    costumer_id BIGINT,
    total_price DECIMAL
    status VARCHAR,
    created_at TIMESTAMP,
    updated_at
)

CREATE TABLE order_items (
    id BIGINT PRIMARY KEY,
    order_id BIGINT REFERENCES orders
    product_id BIGINT
    quantity INT
    price DECIMAL
)