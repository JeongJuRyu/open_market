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
           '2022/09/28 00:00:00',
           '2022/09/28 00:00:00',
           '콜라 주문했는데 오지 않았습니다. 환불 가능한가요?',
           false,
           1,
           null,
           UUID_TO_BIN('9c937a70-12cf-4a83-b594-1293b3f994a8'),
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
       );