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
        UUID_TO_BIN('0eceec16-cdab-4885-a552-066e2df2e3ad'),
        '2022-10-18 17:13:35',
        '2022-10-18 17:13:35',
        '오리 연구소',
        'SHIPPING_WAITING',
        '010-1234-5678',
        '김맥스',
        '문 앞'
    ),
    (
        UUID_TO_BIN('2a39a689-b011-441d-a719-0ce4754baa4c'),
        '2022-10-18 17:13:35',
        '2022-10-18 17:13:35',
        '미금 연구소',
        'SHIPPING_WAITING',
        '010-1234-5678',
        '김맥스',
        '문 앞'
    ),
    (
        UUID_TO_BIN('4cc1f796-5362-11ed-bdc3-0242ac120002'),
        '2022-10-23 18:10:25',
        '2022-10-23 18:10:25',
        '수내타워',
        'SHIPPING_WAITING',
        '010-1234-4321',
        '이맥스',
        '뒷 문'
    ),
    (
        UUID_TO_BIN('f0b09f61-df3a-477e-9870-754d50fd0d95'),
        '2022-10-25 08:20:25',
        '2022-10-25 08:20:25',
        '예미지타워',
        'SHIPPING_WAITING',
        '010-2234-4321',
        '박맥스',
        '정 문'
    ),
    (
        UUID_TO_BIN('1036e864-2a2e-4954-86ac-281aed3d1ab6'),
        '2022-10-25 12:11:25',
        '2022-10-25 12:11:25',
        '창솔아파트',
        'SHIPPING_WAITING',
        '010-0234-4321',
        '류맥스',
        '후 문'
    );