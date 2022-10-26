INSERT INTO pickup (
        id,
        created_at,
        modified_at,
        pickup_type,
        mobile,
        recipient,
        requests
    )
VALUES(
        UUID 'c47aff1a-ea35-4c78-981a-5ceb2a8b4069',
        '2022-10-25 11:13:55',
        '2022-10-25 11:13:55',
        'PICKUP_WAITING',
        '010-7217-2199',
        '김맥스',
        '문 앞'
    ),
    (
        UUID '507807e3-8008-4e93-a409-aeb75e8b66ff',
        '2022-10-25 13:41:29',
        '2022-10-25 13:41:29',
        'PICKUP_WAITING',
        '010-1299-1279',
        '이맥스',
        '1층'
    ),
    (
        UUID '06f6af34-82c2-483c-9d76-632d74ee091a',
        '2022-10-24 19:52:12',
        '2022-10-24 19:52:12',
        'PICKUP_WAITING',
        '010-3091-1920',
        '박맥스',
        '뒷 문'
    ),
    (
        UUID '9b95b030-9ed0-4d7a-ac96-4a81964d5b47',
        '2022-10-25 22:12:12',
        '2022-10-25 22:12:12',
        'PICKUP_WAITING',
        '010-3091-1922',
        '최맥스',
        '30분까지 갈게요'
    ),
    (
        UUID '07934b33-4419-4ca5-a242-01d61190c3aa',
        '2022-10-25 23:42:12',
        '2022-10-25 23:42:12',
        'PICKUP_WAITING',
        '010-3091-1923',
        '류맥스',
        '정각까지 갈게요'
    );