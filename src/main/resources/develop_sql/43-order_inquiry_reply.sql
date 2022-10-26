INSERT INTO order_inquiry_reply (
    order_inquiry_reply_id,
    content,
    order_inquiry_order_inquiry_id,
    created_at,
    modified_at
)
values (
           UUID_TO_BIN('c15aee3e-3c3b-47a0-a8bf-ec699d34ef52'),
           '배송사 지연으로 1주일 더 기다려야 합니다.',
           UUID_TO_BIN('f31bb36d-bf22-4d4f-b4c2-5d459d3197da'),
           '2022/09/30 00:00:00',
           '2022/09/30 00:00:00'
       );
INSERT INTO order_inquiry_reply (
    order_inquiry_reply_id,
    content,
    order_inquiry_order_inquiry_id,
    created_at,
    modified_at
)
values (
           UUID_TO_BIN('dced5813-f0d8-4d2f-9d5b-3e63218c64db'),
           '일반 콜라도 맛있습니다.',
           UUID_TO_BIN('9e38990d-62da-400d-92dd-68198a809a39'),
           '2022/08/29 00:00:00',
           '2022/08/29 00:00:00'
       );
