--liquibase formatted sql

--changeset liquibase:12

-- Inserting data into the "pers_info_role" table. Linking Users and Roles

INSERT INTO pers_info_role (pers_info_id, role_id)
VALUES
    (UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce')),
    (UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045'), UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d')),
    (UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95'), UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0')),
    (UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce')),
    (UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'));

