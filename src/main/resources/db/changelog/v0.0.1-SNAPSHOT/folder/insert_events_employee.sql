--liquibase formatted sql

--changeset liquibase:11

-- Inserting data into the "events_employee" table. Linking events and employees

INSERT INTO events_employee (ev_id, emp_id)
VALUES
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')), -- Michael Johnson
    (UUID_TO_BIN('d4379c09-f871-45a1-93b4-a52d9f91ac57'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')), -- Emily Anderson
    (UUID_TO_BIN('ea243166-62d3-4fa1-b168-ae9fd0dc5d6b'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')), -- Daniel Brown
    (UUID_TO_BIN('e4931cb9-2dea-4a75-bdf2-262d7361f343'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')), -- Olivia Martinez
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd')); -- William Wilson
