CREATE TABLE customers
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE items
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255)   NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wishlists
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id BIGINT NOT NULL,
    item_id     BIGINT NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (item_id) REFERENCES items (id),
    UNIQUE (customer_id, item_id)
);

CREATE TABLE orders
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id  BIGINT         NOT NULL,
    order_date   TIMESTAMP      NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE order_items
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id   BIGINT  NOT NULL,
    item_id    BIGINT  NOT NULL,
    quantity   INTEGER NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (item_id) REFERENCES items (id)
);

CREATE INDEX idx_orders_order_date ON orders (order_date);
CREATE INDEX idx_order_items_item_id ON order_items (item_id);
CREATE INDEX idx_wishlists_customer_id ON wishlists (customer_id);

-- Initial data
-- INSERT INTO customers (name, email)
-- VALUES ('John Doe', 'john.doe@example.com'),
--        ('Jane Smith', 'jane.smith@example.com');
-- INSERT INTO items (name, price)
-- VALUES ('Laptop', 999.99),
--        ('Phone', 599.99),
--        ('Headphones', 99.99);
-- INSERT INTO wishlists (customer_id, item_id)
-- VALUES (1, 1),
--        (1, 2),
--        (2, 3);
-- INSERT INTO orders (customer_id, order_date, total_amount)
-- VALUES (1, '2025-07-03 10:00:00', 1099.98),
--        (2, '2025-07-03 12:00:00', 599.99);
-- INSERT INTO order_items (order_id, item_id, quantity)
-- VALUES (1, 1, 1),
--        (1, 3, 1),
--        (2, 2, 1);