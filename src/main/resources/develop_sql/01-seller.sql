INSERT INTO seller(
        seller_id,
        login_id,
        password,
        seller_email,
        seller_name,
        seller_phone_num,
        address,
        is_deleted
    )
VALUES (
        UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430'),
        'a1',
        'a1',
        'a1@tmax.co.kr',
        '써머슈슈즈',
        '010-1234-1234',
        '경기도 성남시 분당구 탄천상로151번길 20, 월드쇼핑타워 A동 4-5층',
        false
    ),
    (
        UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'),
        'a2',
        'a2',
        'a2@tmax.co.kr',
        'BBK치킨',
        '010-1234-1234',
        '경기도 성남시 분당구 탄천상로151번길 20, 월드쇼핑타워 A동 4-5층',
        false
    ),
    (
        UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),
        'a3',
        'a3',
        'a3@tmax.co.kr',
        '플라워스토어',
        '010-1234-1234',
        '경기도 성남시 분당구 탄천상로151번길 20, 월드쇼핑타워 A동 4-5층',
        false
    ),
    (
        UUID_TO_BIN('54717bb2-2850-40e5-9889-d17d227f1606'),
        'a4',
        'a4',
        'a4@tmax.co.kr',
        '마루돈가',
        '010-1234-1234',
        '경기도 성남시 분당구 탄천상로151번길 20, 월드쇼핑타워 A동 4-5층',
        false
    ),
    (
        UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002'),
        'a5',
        'a5',
        'a5@tmax.co.kr',
        '오리치킨',
        '010-4321-4321',
        '경기도 성남시 분당구 탄천상로152번길 20, 월드쇼핑타워 B동 1-3층',
        false
    );