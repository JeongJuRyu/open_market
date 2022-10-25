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
        UUID '999d603c-0f90-45d3-bdce-6a9ad63e9c0f',
        '2022-10-25 20:15:15',
        '2022-10-25 20:15:15',
        '오리 연구소',
        'CANCEL_WAITING',
        '010-7217-2199',
        '김맥스',
        '문 앞'
    ),
    (
        UUID 'e5603ae2-403b-4803-9c88-d1999215aa1a',
        '2022-10-23 23:41:59',
        '2022-10-23 23:41:59',
        '미금 연구소',
        'CANCEL_WAITING',
        '010-1299-1279',
        '이맥스',
        '1층'
    ),
    (
        UUID '46abbdfa-5f7a-49be-8e5b-82dc3a7e2126',
        '2022-10-24 09:51:12',
        '2022-10-24 09:51:12',
        '수내타워',
        'CANCEL_WAITING',
        '010-3091-1920',
        '박맥스',
        '뒷 문'
    );