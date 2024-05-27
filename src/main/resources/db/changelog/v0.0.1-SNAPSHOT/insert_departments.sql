--liquibase formatted sql

--changeset liquibase:5

-- Inserting department data into the "departments" table
INSERT INTO departments (dep_id, dep_name, dep_phone, dep_email, dep_manager_id)
VALUES
    (UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), 'HR', '+12-345-678-90-24', 'hr@example.com', UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')),
    (UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), 'WAREHOUSE', '+12-345-678-90-25', 'warehouse@example.com', UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')),
    (UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), 'FINANCE', '+12-345-678-90-26', 'finance@example.com', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd')),
    (UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), 'IT', '+12-345-678-90-22', 'operations@example.com', UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')),
    (UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), 'SALES', '+12-345-678-90-23', 'sales@example.com', UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'));
