--liquibase formatted sql

--changeset liquibase:1

-- Inserting roles into the "roles" table

INSERT INTO roles (role_id, role_name)
VALUES
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), 'ROLE_USER'),
    (UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d'), 'ROLE_ADMIN'),
    (UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0'), 'ROLE_MANAGER'),
    (UUID_TO_BIN('10ffff51-6e14-4e87-8421-33f4e53f38ac'), 'ROLE_GUEST');