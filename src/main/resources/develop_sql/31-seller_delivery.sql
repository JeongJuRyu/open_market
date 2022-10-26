INSERT INTO seller_delivery(
    seller_delivery_id,
    shipment_address,
    shipment_address_detail,
    return_address,
    return_address_detail,
    is_deleted,
    is_represent,
    seller_id_seller_id
)
VALUES (
       UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430'),
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-1층 출고지',
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-1층 반품지',
       false,
       true,
       UUID_TO_BIN('2d68d1d0-ed27-46d2-b858-da3f0aa2e430')
   ),
   (
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'),
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-2층 출고지',
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-2층 반품지',
       false,
       true,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5')
   ),
   (
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-3층 출고지',
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-3층 반품지',
       false,
       true,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6')
   ),
   (
       UUID_TO_BIN('54717bb2-2850-40e5-9889-d17d227f1606'),
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-4층 출고지',
       '경기도 성남시 분당구 탄천상로151번길 20',
       '월드쇼핑타워 A동 4-4층 반품지',
       false,
       true,
       UUID_TO_BIN('54717bb2-2850-40e5-9889-d17d227f1606')
   ),
   (
       UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002'),
       '경기도 성남시 분당구 탄천상로152번길 20',
       '월드쇼핑타워 B동 1-3층 출고지',
       '경기도 성남시 분당구 탄천상로152번길 20',
       '월드쇼핑타워 B동 1-3층 반품지',
       false,
       true,
       UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002')
   );
