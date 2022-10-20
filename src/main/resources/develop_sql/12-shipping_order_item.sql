INSERT INTO shipping_order_item (
        id,
        amount,
        name,
        price,
        item_id,
        shipping_fee_id,
        shipping_order_id,
        delivery_order_id
    )
VALUES(
        1,
        21500,
        '뿌린클 순살치킨 세트',
        20000,
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        1,
        NULL,
        1
    ),
    (
        2,
        163000,
        '로토 스르르트 썸머 슈즈',
        81000,
        UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
        2,
        1,
        NULL
    );