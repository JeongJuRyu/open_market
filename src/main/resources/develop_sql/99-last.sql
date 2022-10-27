-- 찜 그룹 등록
INSERT INTO wishlist_group(id, name, position, is_deleted, user_id)
VALUES (
        1,
        '여행용',
        1,
        false,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    );
INSERT INTO wishlist_group(id, name, position, is_deleted, user_id)
VALUES (
        2,
        '나들이용',
        2,
        false,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
    );
-- 찜 상품 등록
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (1, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (2, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (3, UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'), 2, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (4, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb28'), null, false);