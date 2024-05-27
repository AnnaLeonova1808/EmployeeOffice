--liquibase formatted sql

--changeset liquibase:2

-- Inserting rights into the "authorities" table

INSERT INTO authorities (auth_id, authority)
VALUES
    (UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02'), 'READ_DOCUMENT'),
    (UUID_TO_BIN('cfd9f19b-c9e2-4953-ac31-c61803c0baa3'), 'CREATE_DOCUMENT'),
    (UUID_TO_BIN('16001a26-ce5f-423a-afde-4bea256cec90'), 'UPDATE_DOCUMENT'),
    (UUID_TO_BIN('a1a62aae-38fa-4900-b392-c2ce5afbb5da'), 'DELETE_DOCUMENT');