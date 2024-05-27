--liquibase formatted sql

--changeset liquibase:6

-- Inserting employees into the "employees" table

INSERT INTO employees (emp_id, first_name, last_name, position, hire_date, term_date, workplace_location, status_emp, created_at, vac_plan, dep_id, dep_manager_id, sched_id, pers_info_id)
VALUES
    (UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), 'Michael', 'Johnson', 'HR_MANAGER', '2021-03-20', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Flexible Plan', UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe')),
    (UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), 'Emily', 'Anderson', 'STOREKEEPER', '2019-08-10', NULL, 'WAREHOUSE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), UUID_TO_BIN('b6892135-bc87-427b-b23e-be0ccafecb7e'), UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045')),
    (UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), 'Daniel', 'Brown', 'SALES_MANAGER', '2017-05-05', NULL, 'SALES_AGENT_ROUTE', 'VACATION', CURRENT_TIMESTAMP, 'Unlimited Plan', UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), UUID_TO_BIN('f4d730e8-1bef-47ef-ba90-24d28dfc57e6'), UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95')),
    (UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), 'Olivia', 'Martinez', 'PROGRAMMER', '2022-01-15', NULL, 'OFFICE', 'SICK_LEAVE', CURRENT_TIMESTAMP, 'Basic Plan', UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1')),
    (UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), 'William', 'Wilson', 'ECONOMIST', '2020-11-30', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'));
