-- 상품 등록

INSERT INTO item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) VALUES (UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), '로토 스르르트 썸머 슈즈', 81000, 0, UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430'), 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',8, '2019-08-25 12:36:04');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) values (UUID_TO_BIN('8772d6b7-a125-4bf5-9224-88d5270b2633'), '미니원피스', 50000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),  'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','이쁜 상품',60, '2019-08-25 12:36:05');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) values (UUID_TO_BIN('f1c6f586-d9c4-4642-8b0d-55b0565e4520'), '미디원피스', 51000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),  'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45', '이쁜 상품', 61, '2019-08-25 12:36:06');
insert into item (id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) values (UUID_TO_BIN('e7030b3b-c815-4279-9137-e8e294b52078'), '롱원피스', 52000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),  'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45', '이쁜 상품',62, '2019-08-25 12:36:07');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (1, 'SHIPPING', UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (2, 'VISIT', UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'));

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'), 'color', 1, 0, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'));
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'), '굽 선택', 0, 0, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'));

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('d8b52a5a-45e7-424a-beaa-7f281081f1c6'), '블랙', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('6eb20b16-20de-49a3-9957-b19c17639d1e'), '화이트', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('ca24924e-2007-4177-8e5a-a55225c1b2d6'), '레드', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('97caffe3-7134-4c01-8d4b-ad2c28e7258a'), '3cm', 1000, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1'), '5cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('af8cab71-4be5-4948-b21f-7b621d29dc07'), '7cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'));

INSERT INTO item(id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) VALUES (UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), '뿌린클 순살치킨 세트', 20000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'), 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','맛잇는 상품', 5, '2019-08-25 12:36:08');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (3, 'DELIVERY', UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (4, 'PICKUP', UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'), '종류', 1, 0, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'), '소스', 0, 0, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366'), '순살', 1000, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('6513cacf-a7e2-4340-9ecc-2721b229eccf'), '뼈', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('434a6e11-edb0-4dea-80df-90c495bd4c86'), '살사소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('37233135-e75e-4549-8217-939d756199f9'), '마요소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'));

INSERT INTO item(id, name, price, is_deleted, seller_seller_id, shipping_charge_type, shipping_charge, return_charge, return_address, description, category_id, created_at) VALUES (UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'), '킹 의자', 20000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'), 'FREE_SHIPPING', 3000, 2500, '경기 성남시 분당구 정자일로 45','왕중의 왕 의자라네', 39, '2019-08-25 12:36:08');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (5, 'SHIPPING', UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (6, 'VISIT', UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (7, 'DELIVERY', UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (8, 'PICKUP', UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('94d51259-cd75-4a2f-be73-13d7f798fb51'), '색상', 1, 0, UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('86a74cdf-cf9c-4346-9100-9d2096b9e535'), '추가 크기', 0, 0, UUID_TO_BIN('a103af33-3eb0-4efd-a885-bf7cab7644b8'));

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('b54ee7af-0c10-4c2f-bce2-2fb62e42269b'), '레드', 0, '색상 선택', 0, UUID_TO_BIN('94d51259-cd75-4a2f-be73-13d7f798fb51'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('5e4f1976-ca58-4bf7-871c-da0642987317'), '블루', 0, '색상 선택', 0, UUID_TO_BIN('94d51259-cd75-4a2f-be73-13d7f798fb51'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('85c50bd9-ee4d-40cf-a67e-ffd1acf36599'), 'L', 5000, '추가 사이즈 (기본 M)', 0, UUID_TO_BIN('86a74cdf-cf9c-4346-9100-9d2096b9e535'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('4395b166-133f-4adc-8f02-56226dc221f9'), 'XL', 5000, '추가 사이즈 (기본 M)', 0, UUID_TO_BIN('86a74cdf-cf9c-4346-9100-9d2096b9e535'));