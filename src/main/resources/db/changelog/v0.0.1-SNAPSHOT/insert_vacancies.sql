--liquibase formatted sql

--changeset liquibase:7

-- Inserting vacancy data into the "vacancies" table

INSERT INTO vacancies (vacancy_id, position, vacancy_description, vacancy_requirements, vacancy_salary, workplace_location, vacancy_status, vacancy_contact_info, dep_id)
VALUES
    (UUID_TO_BIN('efff9467-a80e-447d-8763-ee7acfa5c29c'), 'HR_MANAGER', 'HR Position Description', 'Requirements for HR manager Position', 90000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c')),
    (UUID_TO_BIN('5c2f14e9-e9f2-4e67-9911-2c3bed21b74d'), 'DRIVER', 'Driver Position Description', 'Requirements for Driver Position', 55000.00, 'WAREHOUSE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc')),
    (UUID_TO_BIN('aa51c3f9-0329-4633-9811-882bd21ec67b'), 'ECONOMIST', 'Security Guard Position Description', 'Requirements for Security Guard Position', 60000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3')),
    (UUID_TO_BIN('51b02a7e-e57c-4321-ba34-73d59bfbddec'), 'PROGRAMMER', 'Receptionist Position Description', 'Requirements for Receptionist Position', 48000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21')),
    (UUID_TO_BIN('6f8fdfd4-419c-46a7-9ba2-33a32723cd6c'), 'SALES_MANAGER', 'Analyst Position Description', 'Requirements for Analyst Position', 75000.00, 'SALES_AGENT_ROUTE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'));
