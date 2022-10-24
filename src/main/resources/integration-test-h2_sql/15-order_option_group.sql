INSERT INTO order_option_group (
        id,
        is_necessary,
        name,
        option_group_id,
        shipping_order_selected_option_id,
        pickup_order_selected_option_id
    )
VALUES(
        1,
        1,
        '종류',
        UUID '53d4caac-64e9-45aa-b606-73b8b51192a7',
        UUID '9c937a70-12cf-4a83-b594-1293b3f994a8',
        NULL
    ),
    (
        2,
        0,
        '소스',
        UUID 'ebe5c005-9241-4149-844a-c0ee1eabb29c',
        UUID '9c937a70-12cf-4a83-b594-1293b3f994a8',
        NULL
    ),
    (
        3,
        1,
        'color',
        UUID '62cfa7ab-26f5-46cf-af80-f9dedfda5693',
        UUID '6148b3fe-7fdd-4344-8938-4d938bd23799',
        NULL
    ),
    (
        4,
        0,
        '굽 선택',
        UUID 'd1cb3733-da60-413e-9574-8085372bbce7',
        UUID '6148b3fe-7fdd-4344-8938-4d938bd23799',
        NULL
    ),
    (
        5,
        1,
        'color',
        UUID '62cfa7ab-26f5-46cf-af80-f9dedfda5693',
        UUID '95547dc4-6d88-4796-b964-ff71aa056b7c',
        NULL
    ),
    (
        6,
        0,
        '굽 선택',
        UUID 'd1cb3733-da60-413e-9574-8085372bbce7',
        UUID '95547dc4-6d88-4796-b964-ff71aa056b7c',
        NULL
    );