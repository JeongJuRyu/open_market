INSERT INTO shop(id, name, is_deleted) VALUES (X'2D68D1D0ED2746D2B858DA3F0AA2E430', '써머슈슈즈123', 0)
INSERT INTO shop(id, name, is_deleted) VALUES (UUID'8d41c42a-011b-4525-9864-b24481f985c5', 'BBK치킨', 0)

INSERT INTO item(id, name, price, is_deleted, shop_id) VALUES (UUID'169f84f8-8862-477c-ad27-0b79871deb27', '로토 스르르트 썸머 슈즈', 81000, 0, UUID'2d68d1d0-ed27-46d2-b858-da3f0aa2e430')

INSERT INTO item_send_type(id, send_type, item_id) VALUES (100, 'SHIPPING', UUID'169f84f8-8862-477c-ad27-0b79871deb27')
INSERT INTO item_send_type(id, send_type, item_id) VALUES (200, 'VISIT', UUID'169f84f8-8862-477c-ad27-0b79871deb27')

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693', 'color', 1, 0, UUID'169f84f8-8862-477c-ad27-0b79871deb27')
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'd1cb3733-da60-413e-9574-8085372bbce7', '굽 선택', 0, 0, UUID'169f84f8-8862-477c-ad27-0b79871deb27')

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'd8b52a5a-45e7-424a-beaa-7f281081f1c6', '블랙', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'6eb20b16-20de-49a3-9957-b19c17639d1e', '화이트', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'ca24924e-2007-4177-8e5a-a55225c1b2d6', '레드', 0, '참을 수 없는 옵션의 유혹', 0, UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'97caffe3-7134-4c01-8d4b-ad2c28e7258a', '3cm', 1000, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1', '5cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'af8cab71-4be5-4948-b21f-7b621d29dc07', '7cm', 0, '참을 수 없는 옵션의 유혹', 0, UUID'd1cb3733-da60-413e-9574-8085372bbce7')

INSERT INTO item(id, name, price, is_deleted, shop_id) VALUES (UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a', '뿌린클 순살치킨 세트', 20000, 0, UUID'8d41c42a-011b-4525-9864-b24481f985c5')

INSERT INTO item_send_type(id, send_type, item_id) VALUES (300, 'DELIVERY', UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a')
INSERT INTO item_send_type(id, send_type, item_id) VALUES (400, 'PICKUP', UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a')

INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'53d4caac-64e9-45aa-b606-73b8b51192a7', '종류', 1, 0, UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a')
INSERT INTO option_group(id, name, is_necessary, is_deleted, item_id) VALUES (UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c', '소스', 0, 0, UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a')

INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366', '순살', 1000, '참을 수 없는 옵션의 유혹', 0, UUID'53d4caac-64e9-45aa-b606-73b8b51192a7')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'6513cacf-a7e2-4340-9ecc-2721b229eccf', '뼈', 0, '참을 수 없는 옵션의 유혹', 0, UUID'53d4caac-64e9-45aa-b606-73b8b51192a7')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'434a6e11-edb0-4dea-80df-90c495bd4c86', '살사소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c')
INSERT INTO item_option(id, name, price, description, is_deleted, option_group_id) VALUES (UUID'37233135-e75e-4549-8217-939d756199f9', '마요소스', 500, '참을 수 없는 옵션의 유혹', 0, UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c')

INSERT INTO cart(id, cart_type) VALUES (1, 'SHIPPING_VISIT')
INSERT INTO cart(id, cart_type) VALUES (2, 'DELIVERY_PICKUP')
INSERT INTO cart(id, cart_type) VALUES (3, 'RESERVATION')

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID'b735da9e-b59a-4caf-80a9-2c894773e447', 'SHIPPING', 0, 1, UUID'169f84f8-8862-477c-ad27-0b79871deb27')

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID'3b711052-5160-4571-9c19-51698066ad34', 1, 0, UUID'b735da9e-b59a-4caf-80a9-2c894773e447')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'248a1a32-fc31-4960-84d4-fc2b49d3b941', UUID'3b711052-5160-4571-9c19-51698066ad34', UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'85c1332c-d982-4d83-99d0-bfae2bfc7c4b', 1, UUID'248a1a32-fc31-4960-84d4-fc2b49d3b941', UUID'd8b52a5a-45e7-424a-beaa-7f281081f1c6')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'83a6f93a-6e29-492b-b9e0-74d4df80bd60', UUID'3b711052-5160-4571-9c19-51698066ad34', UUID'd1cb3733-da60-413e-9574-8085372bbce7')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'e9d9dece-e507-47d2-91d3-7dab2d610658', 1, UUID'83a6f93a-6e29-492b-b9e0-74d4df80bd60', UUID'97caffe3-7134-4c01-8d4b-ad2c28e7258a')

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID'1a3c513c-5fcf-4e5c-984c-9d36a7d71e71', 1, 0, UUID'b735da9e-b59a-4caf-80a9-2c894773e447')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'11eccf0c-bb50-4360-9c98-94409ec81019', UUID'1a3c513c-5fcf-4e5c-984c-9d36a7d71e71', UUID'62cfa7ab-26f5-46cf-af80-f9dedfda5693')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'51f339b9-bb5c-4825-8078-c958bb588e2a', 1, UUID'11eccf0c-bb50-4360-9c98-94409ec81019', UUID'6eb20b16-20de-49a3-9957-b19c17639d1e')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'5e4e0521-b31a-48a2-8e6e-41e62de46a79', UUID'1a3c513c-5fcf-4e5c-984c-9d36a7d71e71', UUID'd1cb3733-da60-413e-9574-8085372bbce7')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'bbf6b483-c37f-4987-ae87-61449ae9984b', 1, UUID'5e4e0521-b31a-48a2-8e6e-41e62de46a79', UUID'74e0b285-c1d4-4b62-b8b8-18f4ab4b46c1')

INSERT INTO cart_item(id, send_type, is_deleted, cart_id, item_id) VALUES (UUID'46ac3f0d-c57f-4878-86ec-e0bea0a88fd6', 'DELIVERY', 0, 2, UUID'1523bc68-e8f7-4140-b7dd-cbfe622e068a')

INSERT INTO selected_option(id, count, is_deleted, cart_item_id) VALUES (UUID'b998c70d-11c9-4903-bdab-9f698c93d83e', 1, 0, UUID'46ac3f0d-c57f-4878-86ec-e0bea0a88fd6')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'17f4206d-00f7-439e-8c74-811fdab6d27c', UUID'b998c70d-11c9-4903-bdab-9f698c93d83e', UUID'53d4caac-64e9-45aa-b606-73b8b51192a7')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'5ea1c05a-1c98-4005-a138-f969980927c3', 1, UUID'17f4206d-00f7-439e-8c74-811fdab6d27c', UUID'afe79f9e-1f2e-45ea-b25f-fc8c3f9c2366')

INSERT INTO cart_option_group(id, selected_option_id, option_group_id) VALUES (UUID'e6fbbb4b-b55e-4f12-a257-d12151579c2f', UUID'b998c70d-11c9-4903-bdab-9f698c93d83e', UUID'ebe5c005-9241-4149-844a-c0ee1eabb29c')
INSERT INTO cart_option(id, count, cart_option_group_id, option_id) VALUES (UUID'302d04cc-d687-4c5b-8331-ced63ad68118', 1, UUID'e6fbbb4b-b55e-4f12-a257-d12151579c2f', UUID'434a6e11-edb0-4dea-80df-90c495bd4c86')
