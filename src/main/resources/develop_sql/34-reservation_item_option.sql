INSERT INTO reservation_item_option(
    option_id,
    option_name,
    option_price,
    option_description,
    is_deleted,
    reservation_item_id_reservation_item_id
)
VALUES (
       UUID_TO_BIN('797d2ce1-4d91-4b39-b9e0-8132f2b02256'),
       '양념 소스',
       '500',
       '양념치킨에 들어가는 양념소스입니다.',
       false,
       UUID_TO_BIN('96655e19-99ef-4436-b5f9-12aac470f855')
   ),
   (
       UUID_TO_BIN('9cde4821-0a85-4d40-9bf0-2abe9dc5f8fe'),
       '기본',
       '0',
       '치킨만 주문합니다.',
       false,
       UUID_TO_BIN('96655e19-99ef-4436-b5f9-12aac470f855')
   ),
   (
       UUID_TO_BIN('7715017f-2edd-48d4-a58b-79fda12a2241'),
       '샐러드 추가',
       '2000',
       '황토 치킨과 같이 먹으면 맛있는 샐러드입니다.',
       false,
       UUID_TO_BIN('b90d04c2-2e69-4f95-a947-224afccf74b3')
   ),
   (
       UUID_TO_BIN('2c939ac8-1530-4adf-b5f7-21a273860194'),
       '기본',
       '0',
       '치킨만 주문합니다.',
       false,
       UUID_TO_BIN('b90d04c2-2e69-4f95-a947-224afccf74b3')
   ),
   (
       UUID_TO_BIN('38146336-e52c-44c7-988e-f733379c04bc'),
       '꽃송이 추가',
       '10000',
       '구성품의 꽃들을 1송이씩 추가합니다.',
       false,
       UUID_TO_BIN('ba9177d5-c328-4559-9ac5-4cef502e0a37')
   ),
   (
       UUID_TO_BIN('648e3f3d-24bc-4bfb-9327-b9189e6105a3'),
       '기본',
       '0',
       '구성된 꽃송이로만 받습니다.',
       false,
       UUID_TO_BIN('ba9177d5-c328-4559-9ac5-4cef502e0a37')
   ),
   (
       UUID_TO_BIN('7c751e87-498c-4957-8944-498ca58a9cd7'),
       '기본',
       '0',
       '구성된 제품으로만 받습니다.',
       false,
       UUID_TO_BIN('1638902d-88d9-4678-a102-16f9c3c6129c')
   ),
   (
       UUID_TO_BIN('62108696-f62a-471d-9686-29d360221bd6'),
       '꽃송이 추가',
       '10000',
       '구성된 꽃들 1송이씩 추가합니다.',
       false,
       UUID_TO_BIN('1638902d-88d9-4678-a102-16f9c3c6129c')
   ),
   (
       UUID_TO_BIN('2b8aa3fa-d3ca-46d1-af45-62823253c36b'),
       '기본',
       '0',
       '치킨만 먹습니다.',
       false,
       UUID_TO_BIN('b7794008-4a01-48d0-9eb2-0c3322ebbba1')
   ),
   (
       UUID_TO_BIN('74ed79e0-226e-4734-aab9-d5af3824e629'),
       '치킨무',
       '500',
       '치킨무 1개를 추가합니다.',
       false,
       UUID_TO_BIN('b7794008-4a01-48d0-9eb2-0c3322ebbba1')
   ),
   (
       UUID_TO_BIN('cd029b1e-76b6-4ae9-ab93-71d6fb6ed97a'),
       '기본',
       '0',
       '치킨만 먹습니다.',
       false,
       UUID_TO_BIN('7e961787-02bb-4052-8095-860c95661b46')
   ),
   (
       UUID_TO_BIN('c1fcecba-ff4a-4da3-bd8e-0f7178c40d50'),
       '치킨무 추가',
       '500',
       '치킨무 1개를 추가합니다.',
       false,
       UUID_TO_BIN('7e961787-02bb-4052-8095-860c95661b46')
   );



