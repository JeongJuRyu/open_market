INSERT INTO order_inquiry (
    order_inquiry_id,
    created_at,
    modified_at,
    content,
    is_replied,
    order_type,
    pickup_order_selected_option_id,
    shipping_order_selected_option_id,
    user_id
)
values (
           UUID_TO_BIN('95d5fed3-6af4-4996-8918-9a4f0b4f094c'),
           '2022/10/11 00:00:00',
           '2022/10/11 00:00:00',
           '콜라 주문했는데 오지 않았습니다. 환불 가능한가요?',
           false,
           'SHIPPINGANDDELIVERY',
           null,
           UUID_TO_BIN('c1fa5a45-ba54-41c3-a689-731166c009ff'),
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
       );

INSERT INTO order_inquiry (
    order_inquiry_id,
    created_at,
    modified_at,
    content,
    is_replied,
    order_type,
    pickup_order_selected_option_id,
    shipping_order_selected_option_id,
    user_id
)
values (
           UUID_TO_BIN('f31bb36d-bf22-4d4f-b4c2-5d459d3197da'),
           '2022/09/28 00:00:00',
           '2022/09/28 00:00:00',
           '주문한지 일주일 지났는데 아직 배송 전이네요. 언제쯤 출고되나요??',
           true,
           'SHIPPINGANDDELIVERY',
           null,
           UUID_TO_BIN('155c2542-fffb-4ca3-9ee9-1e7fa827ba10'),
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
       );