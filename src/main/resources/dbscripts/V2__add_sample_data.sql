-- Insert 20 customers
INSERT INTO customers (name, email, created_at)
VALUES ('John Smith', 'john.smith@example.com', '2025-07-03 10:00:00'),
       ('Emma Johnson', 'emma.johnson@example.com', '2025-07-03 10:01:00'),
       ('Michael Brown', 'michael.brown@example.com', '2025-07-03 10:02:00'),
       ('Sarah Davis', 'sarah.davis@example.com', '2025-07-03 10:03:00'),
       ('David Wilson', 'david.wilson@example.com', '2025-07-03 10:04:00'),
       ('Laura Taylor', 'laura.taylor@example.com', '2025-07-03 10:05:00'),
       ('James Anderson', 'james.anderson@example.com', '2025-07-03 10:06:00'),
       ('Emily White', 'emily.white@example.com', '2025-07-03 10:07:00'),
       ('Robert Martinez', 'robert.martinez@example.com', '2025-07-03 10:08:00'),
       ('Sophie Lee', 'sophie.lee@example.com', '2025-07-03 10:09:00'),
       ('Thomas Clark', 'thomas.clark@example.com', '2025-07-03 10:10:00'),
       ('Olivia Lewis', 'olivia.lewis@example.com', '2025-07-03 10:11:00'),
       ('William Walker', 'william.walker@example.com', '2025-07-03 10:12:00'),
       ('Chloe Hall', 'chloe.hall@example.com', '2025-07-03 10:13:00'),
       ('Daniel Allen', 'daniel.allen@example.com', '2025-07-03 10:14:00'),
       ('Mia Young', 'mia.young@example.com', '2025-07-03 10:15:00'),
       ('Henry King', 'henry.king@example.com', '2025-07-03 10:16:00'),
       ('Ava Scott', 'ava.scott@example.com', '2025-07-03 10:17:00'),
       ('Charles Green', 'charles.green@example.com', '2025-07-03 10:18:00'),
       ('Lily Adams', 'lily.adams@example.com', '2025-07-03 10:19:00');

-- Insert 20 items
INSERT INTO items (name, price, created_at)
VALUES ('Smartphone X', 699.99, '2025-07-03 10:20:00'),
       ('Laptop Pro', 1299.99, '2025-07-03 10:21:00'),
       ('Wireless Headphones', 149.99, '2025-07-03 10:22:00'),
       ('Smart Watch', 249.99, '2025-07-03 10:23:00'),
       ('Gaming Console', 499.99, '2025-07-03 10:24:00'),
       ('Bluetooth Speaker', 99.99, '2025-07-03 10:25:00'),
       ('Tablet 10"', 349.99, '2025-07-03 10:26:00'),
       ('Camera DSLR', 799.99, '2025-07-03 10:27:00'),
       ('Fitness Tracker', 79.99, '2025-07-03 10:28:00'),
       ('External Hard Drive', 119.99, '2025-07-03 10:29:00'),
       ('Wireless Mouse', 29.99, '2025-07-03 10:30:00'),
       ('Mechanical Keyboard', 89.99, '2025-07-03 10:31:00'),
       ('4K Monitor', 399.99, '2025-07-03 10:32:00'),
       ('Smart TV 55"', 599.99, '2025-07-03 10:33:00'),
       ('Portable Charger', 39.99, '2025-07-03 10:34:00'),
       ('Noise-Canceling Earbuds', 199.99, '2025-07-03 10:35:00'),
       ('VR Headset', 349.99, '2025-07-03 10:36:00'),
       ('Smart Home Hub', 129.99, '2025-07-03 10:37:00'),
       ('Action Camera', 249.99, '2025-07-03 10:38:00'),
       ('Wireless Router', 79.99, '2025-07-03 10:39:00');

-- Insert 20 orders
INSERT INTO orders (customer_id, order_date, total_amount, created_at)
VALUES (1, '2025-07-01 09:00:00', 799.98, '2025-07-03 10:40:00'),
       (2, '2025-07-01 10:00:00', 149.99, '2025-07-03 10:41:00'),
       (3, '2025-07-01 11:00:00', 1299.99, '2025-07-03 10:42:00'),
       (4, '2025-07-01 12:00:00', 249.99, '2025-07-03 10:43:00'),
       (5, '2025-07-02 09:00:00', 499.99, '2025-07-03 10:44:00'),
       (6, '2025-07-02 10:00:00', 99.99, '2025-07-03 10:45:00'),
       (7, '2025-07-02 11:00:00', 349.99, '2025-07-03 10:46:00'),
       (8, '2025-07-02 12:00:00', 799.99, '2025-07-03 10:47:00'),
       (9, '2025-07-03 09:00:00', 79.99, '2025-07-03 10:48:00'),
       (10, '2025-07-03 10:00:00', 119.99, '2025-07-03 10:49:00'),
       (11, '2025-07-03 11:00:00', 29.99, '2025-07-03 10:50:00'),
       (12, '2025-07-03 12:00:00', 89.99, '2025-07-03 10:51:00'),
       (13, '2025-07-04 09:00:00', 399.99, '2025-07-03 10:52:00'),
       (14, '2025-07-04 10:00:00', 599.99, '2025-07-03 10:53:00'),
       (15, '2025-07-04 11:00:00', 39.99, '2025-07-03 10:54:00'),
       (16, '2025-07-04 12:00:00', 199.99, '2025-07-03 10:55:00'),
       (17, '2025-07-05 09:00:00', 349.99, '2025-07-03 10:56:00'),
       (18, '2025-07-05 10:00:00', 129.99, '2025-07-03 10:57:00'),
       (19, '2025-07-05 11:00:00', 249.99, '2025-07-03 10:58:00'),
       (20, '2025-07-05 12:00:00', 79.99, '2025-07-03 10:59:00');

-- Insert 20 order_items
INSERT INTO order_items (order_id, item_id, quantity, created_at)
VALUES (1, 1, 2, '2025-07-03 11:00:00'),
       (1, 2, 1, '2025-07-03 11:01:00'),
       (2, 3, 1, '2025-07-03 11:02:00'),
       (3, 2, 1, '2025-07-03 11:03:00'),
       (4, 4, 1, '2025-07-03 11:04:00'),
       (5, 5, 1, '2025-07-03 11:05:00'),
       (6, 6, 1, '2025-07-03 11:06:00'),
       (7, 7, 1, '2025-07-03 11:07:00'),
       (8, 8, 1, '2025-07-03 11:08:00'),
       (9, 9, 1, '2025-07-03 11:09:00'),
       (10, 10, 1, '2025-07-03 11:10:00'),
       (11, 11, 1, '2025-07-03 11:11:00'),
       (12, 12, 1, '2025-07-03 11:12:00'),
       (13, 13, 1, '2025-07-03 11:13:00'),
       (14, 14, 1, '2025-07-03 11:14:00'),
       (15, 15, 1, '2025-07-03 11:15:00'),
       (16, 16, 1, '2025-07-03 11:16:00'),
       (17, 17, 1, '2025-07-03 11:17:00'),
       (18, 18, 1, '2025-07-03 11:18:00'),
       (19, 19, 1, '2025-07-03 11:19:00'),
       (20, 20, 1, '2025-07-03 11:20:00');

-- Insert 20 unique wishlists, avoiding duplicates
INSERT INTO wishlists (customer_id, item_id, created_at)
SELECT c.id, i.id, '2025-07-03 11:21:00'::TIMESTAMP + (row_number() OVER () * interval '1 minute')
FROM (SELECT id
      FROM customers
      WHERE id BETWEEN 1 AND 20
      ORDER BY id LIMIT 20) c
         CROSS JOIN (SELECT id
                     FROM items
                     WHERE id BETWEEN 1 AND 20
                     ORDER BY id LIMIT 20) i
WHERE NOT EXISTS (SELECT 1 FROM wishlists w WHERE w.customer_id = c.id AND w.item_id = i.id) LIMIT 20;