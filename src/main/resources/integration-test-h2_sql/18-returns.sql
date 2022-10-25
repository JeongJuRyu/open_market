INSERT INTO returns (
        id,
        created_at,
        modified_at,
        address,
        return_type,
        mobile,
        recipient,
        requests,
        reason
    )
VALUES(
        UUID 'f7fa70d5-da83-4e2e-90df-49876ef9a64c',
        '2022-10-25 14:14:54',
        '2022-10-25 14:14:54',
        '오리 연구소',
        'RETURN_WAITING',
        '010-7217-2199',
        '김맥스',
        '문 앞',
        '상품이 훼손되었어요'
    ),
    (
        UUID '39e50a7e-03e6-4bf8-a4ad-24cf34994332',
        '2022-10-21 11:11:19',
        '2022-10-21 11:11:19',
        '미금 연구소',
        'RETURN_WAITING',
        '010-1299-1279',
        '이맥스',
        '1층',
        '색깔이 별로에요'
    ),
    (
        UUID 'ef4bafd2-3a88-4ba9-afd5-fb8c0e8935e9',
        '2022-10-24 19:52:12',
        '2022-10-24 19:52:12',
        '수내타워',
        'RETURN_WAITING',
        '010-3091-1920',
        '박맥스',
        '뒷 문',
        '제가 주문한 물건이 아니에요'
    );