--- 상품 등록
INSERT INTO item(id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) VALUES (UUID'169f84f8-8862-477c-ad27-0b79871deb27', '로토 스르르트 썸머 슈즈', 81000, 0, UUID'2d68d1d0-ed27-46d2-b858-da3f0aa2e430', 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',8, '2019-08-25 12:36:04');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type,shipping_charge, return_charge, return_address, description,category_id, created_at) values (UUID'8772d6b7-a125-4bf5-9224-88d5270b2633', '미니원피스', 50000, 0, UUID'8d41c42a-011b-4525-9864-b24481f985c6', 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',60, '2019-08-25 12:36:05');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type,shipping_charge, return_charge, return_address, description,category_id, created_at) values (UUID'f1c6f586-d9c4-4642-8b0d-55b0565e4520', '미디원피스', 51000, 0, UUID'8d41c42a-011b-4525-9864-b24481f985c6', 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45', '이쁜 상품', 61, '2019-08-25 12:36:06');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type,shipping_charge, return_charge, return_address, description,category_id, created_at) values (UUID'e7030b3b-c815-4279-9137-e8e294b52078', '롱원피스', 52000, 0, UUID'8d41c42a-011b-4525-9864-b24481f985c6', 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',62, '2019-08-25 12:36:07');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (1, 'SHIPPING', UUID'169f84f8-8862-477c-ad27-0b79871deb27');
INSERT INTO item_send_type(id, send_type, item_id) VALUES (2, 'VISIT', UUID'169f84f8-8862-477c-ad27-0b79871deb27');

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693', 'color', 1, 0, UUID'169f84f8-8862-477c-ad27-0b79871deb27');
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'd1cb3733-da60-413e-9574-8085372bbce7', '굽 선택', 0, 0, UUID'169f84f8-8862-477c-ad27-0b79871deb27');

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'd8b52a5a-45e7-424a-beaa-7f281081f1c6', '블랙', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'6eb20b16-20de-49a3-9957-b19c17639d1e', '화이트', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'ca24924e-2007-4177-8e5a-a55225c1b2d6', '레드', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'97caffe3-7134-4c01-8d4b-ad2c28e7258a', '3cm', 1000, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1', '5cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'af8cab71-4be5-4948-b21f-7b621d29dc07', '7cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7');

INSERT INTO item(id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description,category_id, created_at) VALUES (UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a', '뿌린클 순살치킨 세트', 20000, 0, UUID'8d41c42a-011b-4525-9864-b24481f985c5', 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',5, '2019-08-25 12:36:08');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (3, 'DELIVERY', UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a');
INSERT INTO item_send_type(id, send_type, item_id) VALUES (4, 'PICKUP', UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a');

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'53d4caac-64e9-45aa-b606-73b8b51192a7', '종류', 1, 0, UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a');
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c', '소스', 0, 0, UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a');

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366', '순살', 1000, '참을 수 없는 옵션의 유혹', 0, UUID'53d4caac-64e9-45aa-b606-73b8b51192a7');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'6513cacf-a7e2-4340-9ecc-2721b229eccf', '뼈', 0, '참을 수 없는 옵션의 유혹', 0, UUID'53d4caac-64e9-45aa-b606-73b8b51192a7');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'434a6e11-edb0-4dea-80df-90c495bd4c86', '살사소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'37233135-e75e-4549-8217-939d756199f9', '마요소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c');

-- 예약 상품 등록

INSERT INTO item(id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) VALUES (UUID'82cdc2eb-5ee8-4bde-8e32-654054b7fc16', '디너코스', 0, 0, UUID'54717bb2-2850-40e5-9889-d17d227f1606','FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품', 5, '2019-08-25 12:36:09');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (5, 'RESERVATION', UUID'82cdc2eb-5ee8-4bde-8e32-654054b7fc16');

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'8b41baa5-6118-4949-886f-abe34ca69cfe', '추가정보', 1, 0, UUID'82cdc2eb-5ee8-4bde-8e32-654054b7fc16');

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'08a1f664-c17c-4ff3-bb26-6519b7b2bff1', 'A코스', 8000, '육회 냉면, 된장찌개, 한우 불고기 런치정식 육회 냉면, 된장찌개, 한우 불괴 런치 정식', 0, UUID'8b41baa5-6118-4949-886f-abe34ca69cfe');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'47360b10-6542-488c-8e59-9ccb324797a8', 'B코스', 42000, '한우+육회+디너코스', 0, UUID'8b41baa5-6118-4949-886f-abe34ca69cfe');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'c46d564c-1e08-4413-be59-9fbc0a1b344c', 'C코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID'8b41baa5-6118-4949-886f-abe34ca69cfe');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'eefe8c5c-15b4-4d5c-9864-5e2e1d47888b', 'D코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID'8b41baa5-6118-4949-886f-abe34ca69cfe');
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'75bd49ea-63e4-4641-a58d-4c24e481a3df', 'E코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID'8b41baa5-6118-4949-886f-abe34ca69cfe');
