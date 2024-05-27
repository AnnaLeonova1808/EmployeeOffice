--liquibase formatted sql

--changeset liquibase:9

-- Inserting addresses into the "addresses" table

INSERT INTO addresses (address_id, country, city, street, house_number, apart_number, address_type, pers_info_id)
VALUES
    (UUID_TO_BIN('b23a92eb-398c-4ba9-9680-b4b3a72a910d'), 'Germany', 'Berlin', 'Hauptstrasse', '123', 'Wohnung 101', 'HOME', UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe')),
    (UUID_TO_BIN('15ef8acb-0980-4074-a8be-0dbb1589ef02'), 'Germany', 'Munich', 'Schlossstrasse', '456', 'Büro 201', 'WORK', UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045')),
    (UUID_TO_BIN('0b751135-128c-46c9-b554-8c6e05bcd2d8'), 'Germany', 'Hamburg', 'Am Markt', '789', 'Büro 301', 'WORK', UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95')),
    (UUID_TO_BIN('53fb4d7f-1f5c-44ae-bf0b-1087275c04dc'), 'Germany', 'Cologne', 'Königsallee', '1010', 'Büro 401', 'WORK', UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1')),
    (UUID_TO_BIN('3c1ed47c-e835-4096-a7e0-73c765b44a64'), 'Germany', 'Frankfurt', 'Bahnhofstrasse', '222', 'Wohnung 102', 'HOME', UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'));
