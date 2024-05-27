--liquibase formatted sql

--changeset liquibase:8

-- Inserting employee vacation data into the "vacations" table

INSERT INTO vacations (vac_id, vac_start_date, vac_end_date, vac_type, vac_category, substitution_emp_id, emp_id)
VALUES
    (UUID_TO_BIN('99b46424-5a60-45a0-b378-cbe9f8a49c73'), '2024-04-01', '2024-04-10', TRUE, 'ANNUAL', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')),
    (UUID_TO_BIN('45c423a1-2a2f-4d2e-b5f3-9a844866164d'), '2024-05-15', '2024-05-25', TRUE, 'MATERNITY', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')),
    (UUID_TO_BIN('530ddcad-d61d-4fc1-9614-3d3f526f3675'), '2024-06-10', '2024-06-20', TRUE, 'PARENTAL', UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')),
    (UUID_TO_BIN('7df490a2-08a9-4c44-8fa0-476c904cd4d5'), '2024-07-01', '2024-07-10', FALSE, 'UNPAID', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')),
    (UUID_TO_BIN('0628ad72-9f21-4dd4-98ea-ee08bcfbd36e'), '2024-08-15', '2024-08-25', TRUE, 'OTHER', UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'));


