--liquibase formatted sql

--changeset liquibase:12
CREATE TABLE IF NOT EXISTS cart_line_item
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    product_amount bigint,
    created timestamp,
    updated timestamp,
    product_id bigint,
    cart_index integer,
    quantity bigint,
    cart_id bigint,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE
    );