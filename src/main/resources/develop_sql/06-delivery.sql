INSERT INTO delivery_address(
        id,
        recipient,
        address,
        mobile,
        requests,
        is_default_address,
        user_id,
        created_at,
        modified_at
)
VALUES (
        UUID_TO_BIN('8289ea9e-8be2-477b-8e43-a758e6a90fc9'),
        '류정주',
        '사랑시 고백구 행복동',
        '010-4523-6994',
        '오이 제발 빼주세요',
        true,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
        '2022/10/12 00:00:00',
        '2022/10/12 00:00:00'
);