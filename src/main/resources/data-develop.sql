-- 카테고리 등록
insert into categories values (1,'여성패션', 0);
insert into categories values (2,'남성패션', 0);
insert into categories values (3,'화장품/미용', 0);
insert into categories values (4,'가구/인테리어', 0);
insert into categories values (5,'식품', 0);
insert into categories values (6,'출산/유아동', 0);

insert into categories values (7,'여성의류', 1);
insert into categories values (8,'여성신발', 1);
insert into categories values (9,'여성가방/지갑', 1);
insert into categories values (10,'주얼리', 1);
insert into categories values (11,'언더웨어/잠옷', 1);
insert into categories values (12,'시계', 1);
insert into categories values (13,'패션잡화/소품', 1);

insert into categories values (51,'원피스', 7);
insert into categories values (52,'상의', 7);
insert into categories values (53,'하의', 7);
insert into categories values (54,'아우터', 7);
insert into categories values (55,'수트/셋업', 7);
insert into categories values (56,'운동복', 7);
insert into categories values (57,'수영복/비치웨어', 7);
insert into categories values (58,'테마의류', 7);
insert into categories values (59,'임산부의류', 7);

insert into categories values (60,'미니', 51);
insert into categories values (61,'미디움', 51);
insert into categories values (62,'롱', 51);

insert into categories values (63,'크롭티', 52);
insert into categories values (64,'긴팔티셔츠', 52);
insert into categories values (65,'반팔티셔츠', 52);
insert into categories values (66,'후드티', 52);
insert into categories values (67,'맨투맨/스웨트셔츠', 52);
insert into categories values (68,'블라우스/셔츠', 52);
insert into categories values (69,'니트/스웨터', 52);

insert into categories values (70,'스커트', 53);
insert into categories values (71,'청바지', 53);
insert into categories values (72,'팬츠', 53);
insert into categories values (73,'오버롤', 53);
insert into categories values (74,'레깅스', 53);

insert into categories values (75,'트위드재킷', 54);
insert into categories values (76,'카디건', 54);
insert into categories values (77,'조끼', 54);
insert into categories values (78,'레인코트', 54);
insert into categories values (79,'바람막이', 54);
insert into categories values (80,'재킷', 54);
insert into categories values (81,'코트', 54);
insert into categories values (82,'점퍼', 54);
insert into categories values (83,'핸드메이드코트', 54);

insert into categories values (84,'수트', 55);
insert into categories values (85,'점프수트', 55);
insert into categories values (86,'트위드셋업', 55);
insert into categories values (87,'니트셋업', 55);
insert into categories values (88,'쇼츠셋업', 55);
insert into categories values (89,'트레이닝셋업', 55);
insert into categories values (90,'패턴셋업', 55);

insert into categories values (91,'요가/필라테스', 56);
insert into categories values (92,'트레이닝상의', 56);
insert into categories values (93,'트레이닝하의', 56);
insert into categories values (94,'트레이닝세트', 56);

insert into categories values (95,'비키니', 57);
insert into categories values (96,'원피스', 57);
insert into categories values (97,'래쉬가드', 57);
insert into categories values (98,'워터레깅스', 57);

insert into categories values (99,'한복', 58);
insert into categories values (100,'파티복', 58);
insert into categories values (101,'코스튬', 58);
insert into categories values (102,'유니폼/단체복', 58);

insert into categories values (103,'언더웨어', 59);
insert into categories values (104,'수유복', 59);
insert into categories values (105,'원피스', 59);
insert into categories values (106,'바지', 59);
insert into categories values (107,'청바지', 59);
insert into categories values (108,'트레이닝복/요가복', 59);
insert into categories values (109,'스커트', 59);
insert into categories values (110,'스타킹', 59);
insert into categories values (111,'레깅스', 59);

insert into categories values (14,'남성의류', 2);
insert into categories values (15,'남성신발', 2);
insert into categories values (16,'남성가방/지갑', 2);
insert into categories values (17,'주얼리', 2);
insert into categories values (18,'언더웨어/잠옷', 2);
insert into categories values (19,'시계', 2);
insert into categories values (20,'패션잡화/소품', 2);

insert into categories values (21,'스킨케어', 3);
insert into categories values (22,'헤어케어', 3);
insert into categories values (23,'바디케어', 3);
insert into categories values (24,'향수', 3);
insert into categories values (25,'클렌징', 3);
insert into categories values (26,'뷰티소품', 3);
insert into categories values (27,'베이스메이크업', 3);
insert into categories values (28,'섹조메이크업', 3);
insert into categories values (29,'마스크/팩', 3);
insert into categories values (30,'네일케어', 3);
insert into categories values (31,'선케어', 3);
insert into categories values (32,'남성화장품', 3);
insert into categories values (33,'비건', 3);

insert into categories values (34,'소파', 4);
insert into categories values (35,'침대/매트리스', 4);
insert into categories values (36,'드레스룸/행거', 4);
insert into categories values (37,'거실장/테이블', 4);
insert into categories values (38,'책상/책장', 4);
insert into categories values (39,'의자', 4);
insert into categories values (40,'식탁/주방가구', 4);
insert into categories values (41,'선반/수납가구', 4);
insert into categories values (42,'아동/주니어', 4);
insert into categories values (43,'조명/스텐드', 4);
insert into categories values (44,'셀프인테리어', 4);
insert into categories values (45,'시공/리모델링', 4);
insert into categories values (46,'홈데코/ 수예', 4);
insert into categories values (47,'침구', 4);
insert into categories values (48,'카페트/러그', 4);
insert into categories values (49,'커튼/블라인드', 4);
insert into categories values (50,'야외가구', 4);

--
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430'), '써머슈슈즈', 0);
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'), 'BBK치킨', 0);
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'), '플라워스토어', 0);

INSERT INTO item(id, name, price, is_deleted, shop_id, category_id, created_at) VALUES (UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), '로토 스르르트 썸머 슈즈', 81000, 0, UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430'), 8, '2019-08-25 12:36:04');
insert into item (id, name, price, is_deleted, shop_id, category_id, created_at) values (UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb28'), '미니원피스', 50000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'), 60, '2019-08-25 12:36:05');
insert into item (id, name, price, is_deleted, shop_id, category_id, created_at) values (UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb29'), '미디원피스', 51000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'), 61, '2019-08-25 12:36:06');
insert into item (id, name, price, is_deleted, shop_id, category_id, created_at) values (UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb30'), '롱원피스', 52000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'), 62, '2019-08-25 12:36:07');

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

INSERT INTO item(id, name, price, is_deleted, shop_id, category_id, created_at) VALUES (UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), '뿌린클 순살치킨 세트', 20000, 0, UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'), 5, '2019-08-25 12:36:08');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (3, 'DELIVERY', UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));
INSERT INTO item_send_type(id, send_type, item_id) VALUES (4, 'PICKUP', UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'), '종류', 1, 0, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'), '소스', 0, 0, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366'), '순살', 1000, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('6513cacf-a7e2-4340-9ecc-2721b229eccf'), '뼈', 0, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('434a6e11-edb0-4dea-80df-90c495bd4c86'), '살사소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('37233135-e75e-4549-8217-939d756199f9'), '마요소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'));

-- 예약 상품 등록
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN('54717bb2-2850-40e5-9889-d17d227f1606'), '마루돈가', 0);

INSERT INTO item(id, name, price, is_deleted, shop_id, category_id, created_at) VALUES (UUID_TO_BIN('82cdc2eb-5ee8-4bde-8e32-654054b7fc16'), '디너코스', 0, 0, UUID_TO_BIN('54717bb2-2850-40e5-9889-d17d227f1606'), 5, '2019-08-25 12:36:09');

INSERT INTO item_send_type(id, send_type, item_id) VALUES (5, 'RESERVATION', UUID_TO_BIN('82cdc2eb-5ee8-4bde-8e32-654054b7fc16'));

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'), '추가정보', 1, 0, UUID_TO_BIN('82cdc2eb-5ee8-4bde-8e32-654054b7fc16'));

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('08a1f664-c17c-4ff3-bb26-6519b7b2bff1'), 'A코스', 8000, '육회 냉면, 된장찌개, 한우 불고기 런치정식 육회 냉면, 된장찌개, 한우 불괴 런치 정식', 0, UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('47360b10-6542-488c-8e59-9ccb324797a8'), 'B코스', 42000, '한우+육회+디너코스', 0, UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('c46d564c-1e08-4413-be59-9fbc0a1b344c'), 'C코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('eefe8c5c-15b4-4d5c-9864-5e2e1d47888b'), 'D코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN('75bd49ea-63e4-4641-a58d-4c24e481a3df'), 'E코스', 150000, '1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리', 0, UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));

-- 카트 등록

INSERT INTO cart(id, cart_type) VALUES (1, 'SHIPPING_VISIT');
INSERT INTO cart(id, cart_type) VALUES (2, 'DELIVERY_PICKUP');
INSERT INTO cart(id, cart_type) VALUES (3, 'RESERVATION');

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN('b735da9e-b59a-4caf-80a9-2c894773e447'), 'SHIPPING', 0, 1, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'));

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN('3b711052-5160-4571-9c19-51698066ad34'), 1, 0, UUID_TO_BIN('b735da9e-b59a-4caf-80a9-2c894773e447'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('248a1a32-fc31-4960-84d4-fc2b49d3b941'), UUID_TO_BIN('3b711052-5160-4571-9c19-51698066ad34'), UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('85c1332c-d982-4d83-99d0-bfae2bfc7c4b'), 1, UUID_TO_BIN('248a1a32-fc31-4960-84d4-fc2b49d3b941'), UUID_TO_BIN('d8b52a5a-45e7-424a-beaa-7f281081f1c6'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('83a6f93a-6e29-492b-b9e0-74d4df80bd60'), UUID_TO_BIN('3b711052-5160-4571-9c19-51698066ad34'), UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('e9d9dece-e507-47d2-91d3-7dab2d610658'), 1, UUID_TO_BIN('83a6f93a-6e29-492b-b9e0-74d4df80bd60'), UUID_TO_BIN('97caffe3-7134-4c01-8d4b-ad2c28e7258a'));

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN('1a3c513c-5fcf-4e5c-984c-9d36a7d71e71'), 1, 0, UUID_TO_BIN('b735da9e-b59a-4caf-80a9-2c894773e447'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('11eccf0c-bb50-4360-9c98-94409ec81019'), UUID_TO_BIN('1a3c513c-5fcf-4e5c-984c-9d36a7d71e71'), UUID_TO_BIN('62cfa7ab-26f5-46cf-af80-f9dedfda5693'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('51f339b9-bb5c-4825-8078-c958bb588e2a'), 1, UUID_TO_BIN('11eccf0c-bb50-4360-9c98-94409ec81019'), UUID_TO_BIN('6eb20b16-20de-49a3-9957-b19c17639d1e'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('5e4e0521-b31a-48a2-8e6e-41e62de46a79'), UUID_TO_BIN('1a3c513c-5fcf-4e5c-984c-9d36a7d71e71'), UUID_TO_BIN('d1cb3733-da60-413e-9574-8085372bbce7'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('bbf6b483-c37f-4987-ae87-61449ae9984b'), 1, UUID_TO_BIN('5e4e0521-b31a-48a2-8e6e-41e62de46a79'), UUID_TO_BIN('74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1'));

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN('46ac3f0d-c57f-4878-86ec-e0bea0a88fd6'), 'DELIVERY', 0, 2, UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'));

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN('b998c70d-11c9-4903-bdab-9f698c93d83e'), 1, 0, UUID_TO_BIN('46ac3f0d-c57f-4878-86ec-e0bea0a88fd6'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('17f4206d-00f7-439e-8c74-811fdab6d27c'), UUID_TO_BIN('b998c70d-11c9-4903-bdab-9f698c93d83e'), UUID_TO_BIN('53d4caac-64e9-45aa-b606-73b8b51192a7'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('5ea1c05a-1c98-4005-a138-f969980927c3'), 1, UUID_TO_BIN('17f4206d-00f7-439e-8c74-811fdab6d27c'), UUID_TO_BIN('afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('e6fbbb4b-b55e-4f12-a257-d12151579c2f'), UUID_TO_BIN('b998c70d-11c9-4903-bdab-9f698c93d83e'), UUID_TO_BIN('ebe5c005-9241-4149-844a-c0ee1eabb29c'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('302d04cc-d687-4c5b-8331-ced63ad68118'), 1, UUID_TO_BIN('e6fbbb4b-b55e-4f12-a257-d12151579c2f'), UUID_TO_BIN('434a6e11-edb0-4dea-80df-90c495bd4c86'));

-- 카트에 예약 상품 등록

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN('0ceda629-b012-45c4-bdf9-6e5787ba4e62'), 'RESERVATION', 0, 3, UUID_TO_BIN('82cdc2eb-5ee8-4bde-8e32-654054b7fc16'));

INSERT INTO cart_reservation_item(id, day_of_week, guest_email, guest_name, guest_phone_number, reservation_date, reservation_headcount, reservation_requirement, cart_item_id) VALUES (1, 'MONDAY', 'abc_12@tmax.co.kr', '김맥스', '010-1234-5678', '2022-08-08 20:00:00', 36, '50인분 같은 36인분 부탁이요 ~', UUID_TO_BIN('0ceda629-b012-45c4-bdf9-6e5787ba4e62'));

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN('370b1115-3425-4672-ba8c-c384e5b9b6ab'), 1, 0, UUID_TO_BIN('0ceda629-b012-45c4-bdf9-6e5787ba4e62'));

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN('5343e1f3-cd8a-43a2-99fc-adf333c6b3c8'), UUID_TO_BIN('370b1115-3425-4672-ba8c-c384e5b9b6ab'), UUID_TO_BIN('8b41baa5-6118-4949-886f-abe34ca69cfe'));
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN('1c80573b-4974-4c8e-8dc3-beebdbac5f3e'), 1, UUID_TO_BIN('5343e1f3-cd8a-43a2-99fc-adf333c6b3c8'), UUID_TO_BIN('47360b10-6542-488c-8e59-9ccb324797a8'));

-- 카테고리 상품

-- 찜 그룹 등록
INSERT INTO wishlist_group(id, name, position, is_deleted) VALUES (1, '여행용', 0, false);
INSERT INTO wishlist_group(id, name, position, is_deleted) VALUES (2, '나들이용', 1, false);

-- 찜 상품 등록
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (1, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (2, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb28'), 1, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (3, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb29'), 2, false);
INSERT INTO wishlist_item(id, item_id, wishlist_group_id, is_deleted) VALUES (4, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb30'), null, false);

-- 유저 등록
INSERT INTO USERS(user_id, email, password, phone_num, address, user_name, nick_name) VALUES (UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'), 'totw2018@naver.com', '1234', '010-4523-6994', '사랑시 행복구 고백동', '류정주', '쩡류');

-- 리뷰 등록
INSERT INTO REVIEW(review_id, content, is_useful, star_rating, title, item_id, user_id) VALUES (UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'), '맛있어요', 23, 4.0, '추천',UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));
INSERT INTO REVIEW(review_id, content, is_useful, star_rating, title, item_id, user_id) VALUES (UUID_TO_BIN('ea9e4424-3641-4bcc-b75a-8ded708c5b4f'), '최곤데요?', 10, 5.0, '강추',UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));
INSERT INTO REVIEW(review_id, content, is_useful, star_rating, title, item_id, user_id) VALUES (UUID_TO_BIN('885d458c-997a-4e67-b410-39fae05606f4'), '어른들 간식, 아이들 술안주로 딱입니다.', 100, 3.0, '그냥 그래요', UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'), UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));

-- 리뷰 이미지 등록
INSERT INTO review_image(review_image_id, url, reivew_id) values (UUID_TO_BIN('a303a658-fca9-4fc4-8603-5d04bdd52126'), 'https://naver.com', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));
INSERT INTO review_image(review_image_id, url, reivew_id) values (UUID_TO_BIN('7de948f4-80ba-43e1-9da6-891b2713a581'), 'https://tmax.co.kr', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));

-- 리뷰 답변 등록

INSERT INTO review_reply(review_reply_id, content, reivew_id) values (UUID_TO_BIN('6d269b51-590c-4ae4-95eb-e0b447b47c24'), '감사합니다.', UUID_TO_BIN('ce093467-1e60-45c9-b073-9b684b9dbc06'));

-- 유저 배송지 등록
INSERT INTO delivery_address(id, address, is_basic_address, name, phone_num, user_id) values (UUID_TO_BIN('143e9261-738b-49a4-99e7-94559560cb35'), '사랑시 고백구 행복동', true, '류정주', '010-4523-6994', UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));
INSERT INTO delivery_address(id, address, is_basic_address, name, phone_num, user_id) values (UUID_TO_BIN('c89ba92a-6417-4478-aa9c-6adc7b2d3698'), '서울시 성북구 솔샘로7길 19, 103호', true, '김덕배', '010-1111-6994', UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'));
