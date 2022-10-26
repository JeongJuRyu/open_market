INSERT INTO
    reservation_item(
    reservation_item_id,
    reservation_item_name,
    reservation_item_price,
    reservation_item_description,
    reservation_item_notice,
    allow_reservation_number_per_interval,
    reservation_interval,
    is_deleted,
    seller_id_seller_id,
    start_time,
    end_time
)
VALUES (
       UUID_TO_BIN('96655e19-99ef-4436-b5f9-12aac470f855'),
       '후라이드 치킨',
       '18000',
       'BBK 치킨의 후라이드 치킨입니다.',
       '튀기는데 15분 정도의 시간이 소요됩니다.',
       3,
       '60',
       false,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'),
       '16:00:00',
       '23:00:00'
   ),
   (
       UUID_TO_BIN('b90d04c2-2e69-4f95-a947-224afccf74b3'),
       '황토 치킨',
       '23000',
       'BBK 치킨의 시그니처 치킨입니다.',
       '준비하는데 30분 정도의 시간이 소요됩니다.',
       3,
       '60',
       false,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c5'),
       '16:00:00',
       '23:00:00'
   ),
   (
       UUID_TO_BIN('ba9177d5-c328-4559-9ac5-4cef502e0a37'),
       '졸업 축하 꽃다발',
       '40000',
       '프리지아, 작약, 라일락, 노란 장미, 튤립으로 구성되어있습니다.',
       '꽃 구성을 바꾸고 싶을시 예약 후 연락주시면 됩니다.',
       10,
       '30',
       false,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),
       '09:00:00',
       '19:00:00'
   ),
   (
       UUID_TO_BIN('1638902d-88d9-4678-a102-16f9c3c6129c'),
       '기념일 꽃다발',
       '50000',
       '장미, 튤립, 아네모네, 안개꽃, 국화로 구성되어있습니다.',
       '꽃 구성을 바꾸고 싶을시 예약 후 연락주시면 됩니다.',
       10,
       '30',
       false,
       UUID_TO_BIN('8d41c42a-011b-4525-9864-b24481f985c6'),
       '09:00:00',
       '19:00:00'
   ),
   (
       UUID_TO_BIN('b7794008-4a01-48d0-9eb2-0c3322ebbba1'),
       '양념 치킨',
       '20000',
       '오리 치킨의 시그니처 양념으로 버무린 치킨입니다.',
       '준비하는데 20분 정도의 시간이 소요됩니다.',
       3,
       '60',
       false,
       UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002'),
       '16:00:00',
       '23:00:00'
   ),
   (
       UUID_TO_BIN('7e961787-02bb-4052-8095-860c95661b46'),
       '옛날 통닭',
       '20000',
       '오리 치킨 사장님이 옛날에 먹었던 통닭을 재현했습니다.',
       '준비하는데 15분 정도의 시간이 소요됩니다.',
       3,
       '60',
       false,
       UUID_TO_BIN('080bdfe8-535f-11ed-bdc3-0242ac120002'),
       '16:00:00',
       '23:00:00'
   );

