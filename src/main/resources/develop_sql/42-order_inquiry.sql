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
           '사이즈가 너무 작게 나왔습니다.환불 가능한가요?',
           false,
           'SHIPPINGANDDELIVERY',
           null,
           0x0BCE37269A144D4CB07D641D3C2BF48A,
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
           0x155C2542FFFB4CA39EE91E7FA827BA10,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
       );

# INSERT INTO order_inquiry (
#     order_inquiry_id,
#     created_at,
#     modified_at,
#     content,
#     is_replied,
#     order_type,
#     pickup_order_selected_option_id,
#     shipping_order_selected_option_id,
#     user_id
# )
# values (
#            UUID_TO_BIN('9e38990d-62da-400d-92dd-68198a809a39'),
#            '2022/08/28 00:00:00',
#            '2022/08/28 00:00:00',
#            '제로콜라를 시켰는데 일반 콜라가 왔습니다. 환불 가능한가요?',
#            true,
#            'SHIPPINGANDDELIVERY',
#            null,
#            UUID_TO_BIN('95547dc4-6d88-4796-b964-ff71aa056b7c'),
#            UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
#        );

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
           UUID_TO_BIN('4f76af76-6e0e-42f7-a135-3dee1261345b'),
           '2022/10/25 00:00:00',
           '2022/10/25 00:00:00',
           '하루가 지났는데 배송이 안옵니다.',
           false,
           'SHIPPINGANDDELIVERY',
           null,
           0x8707C0579A144A8DB6566F62EC8987B7,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28')
       );