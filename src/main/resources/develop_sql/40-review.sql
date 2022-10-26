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
        0x9C937A7012CF4A83B5941293B3F994A8,
        UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
        UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
        '2022/10/01 00:00:00'
);

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
           UUID_TO_BIN('c9774217-3c05-4ef8-900b-f6e54ddb78e5'),
           '저의 야식을 책임집니다.',
           3,
           'SHIPPINGANDDELIVERY',
           false,
           null,
           0x95547DC46D884796B964FF71AA056B7C,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('1523bc68-e8f7-4140-b7dd-cbfe622e068a'),
           '2022/08/30 00:00:00'
       );

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
           UUID_TO_BIN('527669d4-ec56-4370-b18b-a1605b414680'),
           '디자인이 예쁩니다.',
           5,
           'SHIPPINGANDDELIVERY',
           true,
           null,
           0x6148B3FE7FDD434489384D938BD23799,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
           '2022/08/01 00:00:00'
       );

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
           UUID_TO_BIN('983f6810-62c3-4715-90c1-4807b3f53f3e'),
           '사이즈도 딱 맞고 너무 마음에 듭니다.',
           3,
           'SHIPPINGANDDELIVERY',
           false,
           null,
           0x371A2E44EFEE4DA7B29A50CAEA8B0508,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
           '2022/08/01 00:00:00'
       );