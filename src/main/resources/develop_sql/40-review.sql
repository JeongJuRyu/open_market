INSERT INTO review (
        id,
        content,
        star_rating,
        order_type,
        is_replied,
        pickup_order_selected_option_id,
        shipping_order_selected_option_id,
        user_id,
        item_id,
        created_at
    )
values (
        UUID_TO_BIN('50f07073-e0d0-4d67-a474-e3166dff2ce4'),
        '사장님이 친절하고 음식이 맛있습니다.',
        4.5,
        'SHIPPINGANDDELIVERY',
        true,
        null,
        UUID_TO_BIN('9c937a70-12cf-4a83-b594-1293b3f994a8'),
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        '2022/09/01 00:00:00'
);

# INSERT INTO review (
#     id,
#     content,
#     star_rating,
#     order_type,
#     is_replied,
#     pickup_order_selected_option_id,
#     shipping_order_selected_option_id,
#     user_id,
#     item_id,
#     created_at
# )
# values (
#            UUID_TO_BIN('527669d4-ec56-4370-b18b-a1605b414680'),
#            '디자인이 예쁩니다.',
#            5,
#            'SHIPPINGANDDELIVERY',
#            true,
#            null,
#            UUID_TO_BIN('9c937a70-12cf-4a83-b594-1293b3f994a8'),
#            UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
#            UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
#            '2022/07/01 00:00:00'
#        );