INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(1, 163000, '로토 스르르트 썸머 슈즈', 81000, 0x169F84F88862477CAD270B79871DEB27, 3, 1, NULL);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(2, 45000, '킹 신발', 20000, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), 4, NULL, 1);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(3, 45000, '킹 신발', 20000, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'), 6, 2, NULL);

INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(1, 41500, '뿌린클 순살치킨 세트', 20000, 0x1523BC68E8F74140B7DDCBFE622E068A, 1, NULL, 1);
INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(2, 45000, '킹 신발', 20000, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb32'), 2, NULL, 2);
INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(3, 45000, '킹 신발', 20000, UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb32'), 5, 1, NULL);
