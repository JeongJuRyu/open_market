INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN("2d68d1d0-ed27-46d2-b858-da3f0aa2e430"), "써머슈슈즈", 0)
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN("8d41c42a-011b-4525-9864-b24481f985c5"), "BBK치킨", 0)

INSERT INTO item(id, name, price, is_deleted, shop_id) VALUES (UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"), "로토 스르르트 썸머 슈즈", 81000, 0, UUID_TO_BIN("2d68d1d0-ed27-46d2-b858-da3f0aa2e430"))

INSERT INTO item_send_type(id, send_type, item_id) VALUES (1, "SHIPPING", UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"))
INSERT INTO item_send_type(id, send_type, item_id) VALUES (2, "VISIT", UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"))

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"), "color", 1, 0, UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"))
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"), "굽 선택", 0, 0, UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"))

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("d8b52a5a-45e7-424a-beaa-7f281081f1c6"), "블랙", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("6eb20b16-20de-49a3-9957-b19c17639d1e"), "화이트", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("ca24924e-2007-4177-8e5a-a55225c1b2d6"), "레드", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("97caffe3-7134-4c01-8d4b-ad2c28e7258a"), "3cm", 1000, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1"), "5cm", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("af8cab71-4be5-4948-b21f-7b621d29dc07"), "7cm", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"))

INSERT INTO item(id, name, price, is_deleted, shop_id) VALUES (UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"), "뿌린클 순살치킨 세트", 20000, 0, UUID_TO_BIN("8d41c42a-011b-4525-9864-b24481f985c5"))

INSERT INTO item_send_type(id, send_type, item_id) VALUES (3, "DELIVERY", UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"))
INSERT INTO item_send_type(id, send_type, item_id) VALUES (4, "PICKUP", UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"))

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN("53d4caac-64e9-45aa-b606-73b8b51192a7"), "종류", 1, 0, UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"))
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN("ebe5c005-9241-4149-844a-c0ee1eabb29c"), "소스", 0, 0, UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"))

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366"), "순살", 1000, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("53d4caac-64e9-45aa-b606-73b8b51192a7"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("6513cacf-a7e2-4340-9ecc-2721b229eccf"), "뼈", 0, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("53d4caac-64e9-45aa-b606-73b8b51192a7"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("434a6e11-edb0-4dea-80df-90c495bd4c86"), "살사소스", 500, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("ebe5c005-9241-4149-844a-c0ee1eabb29c"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("37233135-e75e-4549-8217-939d756199f9"), "마요소스", 500, "참을 수 없는 옵션의 유혹", 0, UUID_TO_BIN("ebe5c005-9241-4149-844a-c0ee1eabb29c"))

-- 예약 상품 등록
INSERT INTO shop(id, name, is_deleted) VALUES (UUID_TO_BIN("54717bb2-2850-40e5-9889-d17d227f1606"), "마루돈가", 0)

INSERT INTO item(id, name, price, is_deleted, shop_id) VALUES (UUID_TO_BIN("82cdc2eb-5ee8-4bde-8e32-654054b7fc16"), "디너코스", 0, 0, UUID_TO_BIN("54717bb2-2850-40e5-9889-d17d227f1606"))

INSERT INTO item_send_type(id, send_type, item_id) VALUES (5, "RESERVATION", UUID_TO_BIN("82cdc2eb-5ee8-4bde-8e32-654054b7fc16"))

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"), "추가정보", 1, 0, UUID_TO_BIN("82cdc2eb-5ee8-4bde-8e32-654054b7fc16"))

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("08a1f664-c17c-4ff3-bb26-6519b7b2bff1"), "A코스", 8000, "육회 냉면, 된장찌개, 한우 불고기 런치정식 육회 냉면, 된장찌개, 한우 불괴 런치 정식", 0, UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("47360b10-6542-488c-8e59-9ccb324797a8"), "B코스", 42000, "한우+육회+디너코스", 0, UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("c46d564c-1e08-4413-be59-9fbc0a1b344c"), "C코스", 150000, "1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리", 0, UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("eefe8c5c-15b4-4d5c-9864-5e2e1d47888b"), "D코스", 150000, "1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리", 0, UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID_TO_BIN("75bd49ea-63e4-4641-a58d-4c24e481a3df"), "E코스", 150000, "1등급 한우 코스 요리 1등급 한우 코스 요리 1등급 한우 코스 요리", 0, UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))

-- 카트 등록

INSERT INTO cart(id, cart_type) VALUES (1, "SHIPPING_VISIT")
INSERT INTO cart(id, cart_type) VALUES (2, "DELIVERY_PICKUP")
INSERT INTO cart(id, cart_type) VALUES (3, "RESERVATION")

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN("b735da9e-b59a-4caf-80a9-2c894773e447"), "SHIPPING", 0, 1, UUID_TO_BIN("169f84f8-8862-477c-ad27-0b79871deb27"))

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN("3b711052-5160-4571-9c19-51698066ad34"), 1, 0, UUID_TO_BIN("b735da9e-b59a-4caf-80a9-2c894773e447"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("248a1a32-fc31-4960-84d4-fc2b49d3b941"), UUID_TO_BIN("3b711052-5160-4571-9c19-51698066ad34"), UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("85c1332c-d982-4d83-99d0-bfae2bfc7c4b"), 1, UUID_TO_BIN("248a1a32-fc31-4960-84d4-fc2b49d3b941"), UUID_TO_BIN("d8b52a5a-45e7-424a-beaa-7f281081f1c6"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("83a6f93a-6e29-492b-b9e0-74d4df80bd60"), UUID_TO_BIN("3b711052-5160-4571-9c19-51698066ad34"), UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("e9d9dece-e507-47d2-91d3-7dab2d610658"), 1, UUID_TO_BIN("83a6f93a-6e29-492b-b9e0-74d4df80bd60"), UUID_TO_BIN("97caffe3-7134-4c01-8d4b-ad2c28e7258a"))

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN("1a3c513c-5fcf-4e5c-984c-9d36a7d71e71"), 1, 0, UUID_TO_BIN("b735da9e-b59a-4caf-80a9-2c894773e447"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("11eccf0c-bb50-4360-9c98-94409ec81019"), UUID_TO_BIN("1a3c513c-5fcf-4e5c-984c-9d36a7d71e71"), UUID_TO_BIN("62cfa7ab-26f5-46cf-af80-f9dedfda5693"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("51f339b9-bb5c-4825-8078-c958bb588e2a"), 1, UUID_TO_BIN("11eccf0c-bb50-4360-9c98-94409ec81019"), UUID_TO_BIN("6eb20b16-20de-49a3-9957-b19c17639d1e"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("5e4e0521-b31a-48a2-8e6e-41e62de46a79"), UUID_TO_BIN("1a3c513c-5fcf-4e5c-984c-9d36a7d71e71"), UUID_TO_BIN("d1cb3733-da60-413e-9574-8085372bbce7"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("bbf6b483-c37f-4987-ae87-61449ae9984b"), 1, UUID_TO_BIN("5e4e0521-b31a-48a2-8e6e-41e62de46a79"), UUID_TO_BIN("74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1"))

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN("46ac3f0d-c57f-4878-86ec-e0bea0a88fd6"), "DELIVERY", 0, 2, UUID_TO_BIN("1523bc68-e8f7-4140-b7dd-cbfe622e068a"))

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN("b998c70d-11c9-4903-bdab-9f698c93d83e"), 1, 0, UUID_TO_BIN("46ac3f0d-c57f-4878-86ec-e0bea0a88fd6"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("17f4206d-00f7-439e-8c74-811fdab6d27c"), UUID_TO_BIN("b998c70d-11c9-4903-bdab-9f698c93d83e"), UUID_TO_BIN("53d4caac-64e9-45aa-b606-73b8b51192a7"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("5ea1c05a-1c98-4005-a138-f969980927c3"), 1, UUID_TO_BIN("17f4206d-00f7-439e-8c74-811fdab6d27c"), UUID_TO_BIN("afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("e6fbbb4b-b55e-4f12-a257-d12151579c2f"), UUID_TO_BIN("b998c70d-11c9-4903-bdab-9f698c93d83e"), UUID_TO_BIN("ebe5c005-9241-4149-844a-c0ee1eabb29c"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("302d04cc-d687-4c5b-8331-ced63ad68118"), 1, UUID_TO_BIN("e6fbbb4b-b55e-4f12-a257-d12151579c2f"), UUID_TO_BIN("434a6e11-edb0-4dea-80df-90c495bd4c86"))

-- 카트에 예약 상품 등록

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID_TO_BIN("0ceda629-b012-45c4-bdf9-6e5787ba4e62"), "RESERVATION", 0, 3, UUID_TO_BIN("82cdc2eb-5ee8-4bde-8e32-654054b7fc16"))

INSERT INTO cart_reservation_item(id, day_of_week, guest_email, guest_name, guest_phone_number, reservation_date, reservation_headcount, reservation_requirement, cart_item_id) VALUES (1, "MONDAY", "abc_12@tmax.co.kr", "김맥스", "010-1234-5678", "2022-08-08 20:00:00", 36, "50인분 같은 36인분 부탁이요 ~", UUID_TO_BIN("0ceda629-b012-45c4-bdf9-6e5787ba4e62"))

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID_TO_BIN("370b1115-3425-4672-ba8c-c384e5b9b6ab"), 1, 0, UUID_TO_BIN("0ceda629-b012-45c4-bdf9-6e5787ba4e62"))

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID_TO_BIN("5343e1f3-cd8a-43a2-99fc-adf333c6b3c8"), UUID_TO_BIN("370b1115-3425-4672-ba8c-c384e5b9b6ab"), UUID_TO_BIN("8b41baa5-6118-4949-886f-abe34ca69cfe"))
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID_TO_BIN("1c80573b-4974-4c8e-8dc3-beebdbac5f3e"), 1, UUID_TO_BIN("5343e1f3-cd8a-43a2-99fc-adf333c6b3c8"), UUID_TO_BIN("47360b10-6542-488c-8e59-9ccb324797a8"))
