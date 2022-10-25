INSERT INTO shipping (
        id,
        created_at,
        modified_at,
        address,
        shipping_type,
        mobile,
        recipient,
        requests
    )
VALUES(
        UUID '0eceec16-cdab-4885-a552-066e2df2e3ad',
        '2022-10-18 17:13:35',
        '2022-10-18 17:13:35',
        '오리 연구소',
        'SHIPPING_WAITING',
        '010-1234-5678',
        '김맥스',
        '문 앞'
    ),
    (
        UUID '2a39a689-b011-441d-a719-0ce4754baa4c',
        '2022-10-18 17:13:35',
        '2022-10-18 17:13:35',
        '미금 연구소',
        'SHIPPING_WAITING',
        '010-1234-5678',
        '김맥스',
        '문 앞'
    ),
    (
        UUID '4cc1f796-5362-11ed-bdc3-0242ac120002',
        '2022-10-23 18:10:25',
        '2022-10-23 18:10:25',
        '수내타워',
        'SHIPPING_WAITING',
        '010-1234-4321',
        '이맥스',
        '뒷 문'
    ),
    (
        UUID 'ed925e46-9d1a-4ba1-9992-c16b756ddd36',
        '2022-10-25 20:11:12',
        '2022-10-25 20:11:12',
        '예미지타워',
        'SHIPPING_WAITING',
        '010-1266-6231',
        '한맥스',
        '3층'
    );