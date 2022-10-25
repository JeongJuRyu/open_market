INSERT INTO orders(
        id,
        user_id,
        payment_id
    )
VALUES (
        UUID_TO_BIN('14fdf49d-9560-45c9-a333-cbe6c29386c9'),
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
        1
    ),
    (
        UUID_TO_BIN('e7be24e8-535f-11ed-bdc3-0242ac120002'),
        UUID_TO_BIN('48dfab6c-535f-11ed-bdc3-0242ac120002'),
        2
    );
INSERT INTO delivery_order(
        id,
        amount,
        order_id,
        seller_seller_id
    )
VALUES (
        1,
        21500,
        UUID_TO_BIN('14fdf49d-9560-45c9-a333-cbe6c29386c9'),
        UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430')
    ),
    (
        2,
        15000,
        UUID_TO_BIN('e7be24e8-535f-11ed-bdc3-0242ac120002'),
        UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430')
    );
INSERT INTO shipping_order(
        id,
        amount,
        order_id,
        seller_seller_id
    )
VALUES (
        1,
        16300,
        UUID_TO_BIN('14fdf49d-9560-45c9-a333-cbe6c29386c9'),
        UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430')
    ),
    (
        2,
        9900,
        UUID_TO_BIN('e7be24e8-535f-11ed-bdc3-0242ac120002'),
        UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002')
    );