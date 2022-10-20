-- 찜 그룹 등록
INSERT INTO wishlist_group(id, name, position, is_deleted, user_user_id) VALUES (1, '여행용', 1, false, UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));
INSERT INTO wishlist_group(id, name, position, is_deleted, user_user_id) VALUES (2, '나들이용', 2, false, UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));

-- 찜 상품 등록
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted)
VALUES (
        1,
        UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
        1,
        false
    );
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted)
VALUES (
        2,
        UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb28'),
        1,
        false
    );
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted)
VALUES (
        3,
        UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb29'),
        2,
        false
    );
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted)
VALUES (
        4,
        UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb30'),
        null,
        false
    );
-- 리뷰 등록
INSERT INTO review(
        review_id,
        content,
        is_useful,
        star_rating,
        title,
        item_id,
        order_item_id,
        user_user_id
    )
VALUES (
        UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'),
        '맛있어요',
        23,
        4.0,
        '추천',
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        1,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    ),
    (
        UUID_TO_BIN('ea9e4424-3641-4bcc-b75a-8ded708c5b4f'),
        '최곤데요?',
        10,
        5.0,
        '강추',
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        1,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    ),
    (
        UUID_TO_BIN('885d458c-997a-4e67-b410-39fae05606f4'),
        '어른들 간식, 아이들 술안주로 딱입니다.',
        100,
        3.0,
        '그냥 그래요',
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        1,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    );
-- 리뷰 이미지 등록
-- INSERT INTO review_image(review_image_id, url, reivew_id) values (UUID_TO_BIN('a303a658-fca9-4fc4-8603-5d04bdd52126'), 'https://naver.com', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));
-- INSERT INTO review_image(review_image_id, url, reivew_id) values (UUID_TO_BIN('7de948f4-80ba-43e1-9da6-891b2713a581'), 'https://tmax.co.kr', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));
-- 리뷰 답변 등록
-- INSERT INTO review_reply(review_reply_id, content, reivew_id) values (UUID_TO_BIN('6d269b51-590c-4ae4-95eb-e0b447b47c24'), '감사합니다.', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));
-- 유저 배송지 등록
INSERT INTO delivery_address(
        id,
        address,
        is_default_address,
        name,
        phone_num,
        user_user_id
    )
values (
        UUID_TO_BIN('143e9261-738b-49a4-99e7-94559560cb35'),
        '사랑시 고백구 행복동',
        true,
        '류정주',
        '010-4523-6994',
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    ),
    (
        UUID_TO_BIN('c89ba92a-6417-4478-aa9c-6adc7b2d3698'),
        '서울시 성북구 솔샘로7길 19, 103호',
        true,
        '김덕배',
        '010-1111-6994',
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    );
