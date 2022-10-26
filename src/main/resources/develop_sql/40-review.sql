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
           '사이즈도 맞고 좋습니다',
           4.5,
           'SHIPPINGANDDELIVERY',
           true,
           null,
           0x0BCE37269A144D4CB07D641D3C2BF48A,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
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
           '너무 예쁩니다.',
           3,
           'SHIPPINGANDDELIVERY',
           false,
           null,
           0x155C2542FFFB4CA39EE91E7FA827BA10,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
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
           0x8707C0579A144A8DB6566F62EC8987B7,
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
           0x8707C0579A144A8DB6566F62EC8987B7,
           UUID_TO_BIN('672ffb8c-f952-49ec-b65b-4fe3a9c37b28'),
           UUID_TO_BIN('169f84f8-8862-477c-ad27-0b79871deb27'),
           '2022/08/01 00:00:00'
       );