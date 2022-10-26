INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(1, 21500, '뿌린클 순살치킨 세트', 20000, 0x1523BC68E8F74140B7DDCBFE622E068A, 1, NULL, 1);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(2, 163000, '로토 스르르트 썸머 슈즈', 81000, 0x169F84F88862477CAD270B79871DEB27, 2, 1, NULL);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(3, 9900, '롱원피스', 15000, 0xE7030B3BC81542799137E8E294B52078, 3, 1, NULL);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(4, 45000, '킹 신발', 20000, 0xA103AF333EB04EFDA885BF7CAB7644B8, 4, NULL, 2);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(5, 45000, '킹 신발', 20000, 0xA103AF333EB04EFDA885BF7CAB7644B8, 5, 3, NULL);
INSERT INTO shipping_order_item (id, amount, name, price, item_id, shipping_fee_id, shipping_order_id, delivery_order_id) VALUES(6, 163000, '로토 스르르트 썸머 슈즈', 81000, 0x169F84F88862477CAD270B79871DEB27, 9, 4, NULL);

INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(1, 45000, '킹 신발', 20000, 0xA103AF333EB04EFDA885BF7CAB7644B8, 6, 1, NULL);
INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(2, 45000, '킹 신발', 20000, 0xA103AF333EB04EFDA885BF7CAB7644B8, 7, NULL, 1);
INSERT INTO pickup_order_item (id, amount, name, price, item_id, shipping_fee_id, visit_order_id, pickup_order_id) VALUES(3, 41500, '뿌린클 순살치킨 세트', 20000, 0x1523BC68E8F74140B7DDCBFE622E068A, 8, NULL, 2);
