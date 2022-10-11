--- 유저 등록
INSERT INTO users(user_id, email, password, phone_num, address, name) VALUES (UUID'672ffb8c-f952-49ec-b65b-4fe3a9c37b28', 'totw2018@naver.com', '1234', '010-4523-6994', '사랑시 행복구 고백동', '류정주');

--- 찜 그룹 등록
INSERT INTO wishlist_group(id, name, position, is_deleted) VALUES (1, '여행용', 1, false);
INSERT INTO wishlist_group(id, name, position, is_deleted) VALUES (2, '나들이용', 2, false);

--- 찜 상품 등록
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (1, UUID'169f84f8-8862-477c-ad27-0b79871deb27', 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (2, UUID'169f84f8-8862-477c-ad27-0b79871deb28', 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (3, UUID'169f84f8-8862-477c-ad27-0b79871deb29', 2, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (4, UUID'169f84f8-8862-477c-ad27-0b79871deb30', null, false);