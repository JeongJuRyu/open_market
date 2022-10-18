INSERT INTO order_option (
        id,
        count,
        name,
        price,
        option_id,
        order_option_group_id
    )
VALUES(
        1,
        1,
        '순살',
        1000,
        UUID 'afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366',
        1
    ),
    (
        2,
        1,
        '살사소스',
        500,
        UUID '434a6e11-edb0-4dea-80df-90c495bd4c86',
        2
    ),
    (
        3,
        1,
        '화이트',
        0,
        UUID '6eb20b16-20de-49a3-9957-b19c17639d1e',
        3
    ),
    (
        4,
        1,
        '5cm',
        0,
        UUID '74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1',
        4
    ),
    (
        5,
        1,
        '블랙',
        0,
        UUID 'd8b52a5a-45e7-424a-beaa-7f281081f1c6',
        5
    ),
    (
        6,
        1,
        '3cm',
        1000,
        UUID '97caffe3-7134-4c01-8d4b-ad2c28e7258a',
        6
    );