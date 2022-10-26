--- 찜 그룹 등록
INSERT INTO wishlist_group(id, name, position, is_deleted, user_id) VALUES (1, '여행용', 1, false, UUID'672ffb8c-f952-49ec-b65b-4fe3a9c37b28');
INSERT INTO wishlist_group(id, name, position, is_deleted, user_id) VALUES (2, '나들이용', 2, false, UUID'672ffb8c-f952-49ec-b65b-4fe3a9c37b28');

--- 찜 상품 등록
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (1, UUID'169f84f8-8862-477c-ad27-0b79871deb27', 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (2, UUID'8772d6b7-a125-4bf5-9224-88d5270b2633', 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (3, UUID'f1c6f586-d9c4-4642-8b0d-55b0565e4520', 2, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (4, UUID'e7030b3b-c815-4279-9137-e8e294b52078', null, false);