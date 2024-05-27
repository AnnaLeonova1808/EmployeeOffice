--liquibase formatted sql

--changeset liquibase:13

-- Inserting data into the "role_authority" table. Linking roles and rights

INSERT INTO role_authority (role_id, auth_id)
VALUES
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02')),
    (UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d'), UUID_TO_BIN('cfd9f19b-c9e2-4953-ac31-c61803c0baa3')),
    (UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0'), UUID_TO_BIN('16001a26-ce5f-423a-afde-4bea256cec90')),
    (UUID_TO_BIN('10ffff51-6e14-4e87-8421-33f4e53f38ac'), UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02')),
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), UUID_TO_BIN('a1a62aae-38fa-4900-b392-c2ce5afbb5da'));

