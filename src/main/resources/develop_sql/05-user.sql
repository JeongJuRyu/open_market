INSERT INTO users(
        id,
        email,
        password,
        phone_num,
        address,
        name,
        account_non_expired,
        account_non_locked,
        is_deleted
    )
VALUES (
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
        'totw2018@naver.com',
        '1234',
        '010-4523-6994',
        '사랑시 행복구 고백동',
        '류정주',
        true,
        true,
        false
    );
