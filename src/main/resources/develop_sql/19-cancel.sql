INSERT INTO cancel (
        id,
        created_at,
        modified_at,
        address,
        cancel_type,
        mobile,
        recipient,
        requests
    )
VALUES(
        UUID_TO_BIN('999d603c-0f90-45d3-bdce-6a9ad63e9c0f'),
        '2022-10-25 20:15:15',
        '2022-10-25 20:15:15',
        '오리 연구소',
        'CANCEL_WAITING',
        '010-7217-2199',
        '김맥스',
        '문 앞'
    ),
    (
        UUID_TO_BIN('e5603ae2-403b-4803-9c88-d1999215aa1a'),
        '2022-10-23 23:41:59',
        '2022-10-23 23:41:59',
        '미금 연구소',
        'CANCEL_WAITING',
        '010-1299-1279',
        '이맥스',
        '1층'
    ),
    (
        UUID_TO_BIN('46abbdfa-5f7a-49be-8e5b-82dc3a7e2126'),
        '2022-10-24 09:51:12',
        '2022-10-24 09:51:12',
        '수내타워',
        'CANCEL_WAITING',
        '010-3091-1920',
        '박맥스',
        '뒷 문'
    ),
    (
        UUID_TO_BIN('11e87df6-1603-4c9d-85f3-44a3c660bbf6'),
        '2022-10-25 07:11:32',
        '2022-10-25 07:11:32',
        '예미지타워',
        'CANCEL_WAITING',
        '010-3091-2920',
        '한맥스',
        '정 문'
    ),
    (
        UUID_TO_BIN('5d807a31-bf50-437d-bb64-df114bd7d1ba'),
        '2022-10-25 19:11:12',
        '2022-10-25 19:11:12',
        '판교데이터센터',
        'CANCEL_WAITING',
        '010-3191-1920',
        '류맥스',
        '후 문'
    );